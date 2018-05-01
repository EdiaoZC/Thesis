package com.thesis.service.impl;

import com.thesis.common.exception.RequestAlreadyException;
import com.thesis.common.exception.TimeoutException;
import com.thesis.common.model.DeferResult;
import com.thesis.common.model.Device;
import com.thesis.common.model.Response;
import com.thesis.common.model.RunningParam;
import com.thesis.common.model.form.DeviceForm;
import com.thesis.common.model.form.DeviceRequestForm;
import com.thesis.common.model.vo.DeviceRequestVo;
import com.thesis.common.model.vo.DeviceVo;
import com.thesis.common.model.vo.TrainingResultVo;
import com.thesis.dao.mapper.DeviceMapper;
import com.thesis.service.DeviceService;
import com.thesis.service.TrainingResultService;
import com.thesis.service.WebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/9 15:39
 * @Description:
 */
@Service
@Slf4j
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private WebSocketService webSocketService;
    private ConcurrentHashMap<String, DeviceRequestForm> request;
    @Autowired
    private TrainingResultService resultService;


    private ConcurrentHashMap<String, DeferResult<RunningParam>> deferredHolder;

    private Lock lock;

    private Condition taskComplete;

    private AtomicIntegerFieldUpdater<DeferResult> fieldUpdater;

    public DeviceServiceImpl() {
        this.deferredHolder = new ConcurrentHashMap<>();
        this.lock = new ReentrantLock();
        this.taskComplete = lock.newCondition();
        fieldUpdater = AtomicIntegerFieldUpdater.newUpdater(DeferResult.class, "status");
        this.request = new ConcurrentHashMap<>();
    }


    @Override
    public boolean addDevice(DeviceForm deviceForm) {
        Device device = new Device();
        BeanUtils.copyProperties(deviceForm, device);
        device.setEquipmentType(deviceForm.getDeviceType());
        return deviceMapper.insertSelective(device) == 1;
    }

    @Override
    public boolean delDevice(Short deviceId) {
        return deviceMapper.deleteByPrimaryKey(deviceId) == 1;
    }

    @Override
    public RunningParam requestDevice(String token, DeviceRequestForm form) throws TimeoutException {
        DeferResult<RunningParam> deferResult = new DeferResult<>();
        deferredHolder.put(token, deferResult);
        request.put(token, form);
        webSocketService.sendMessage(token);
        try {
            lock.lock();
            while (deferResult.status != DeferResult.COMPLETE) {
                taskComplete.await();
            }
        } catch (InterruptedException e) {
            log.error("出现错误:{}", e);
        } finally {
            lock.unlock();
        }
        return (RunningParam) deferResult.getResult();
    }


    @Override
    public Response<String> handleRequest(String token, RunningParam param) throws RequestAlreadyException {
        DeferResult<RunningParam> deferResult = deferredHolder.get(token);
        try {
            lock.lock();
            deferResult.setStatus(DeferResult.COMPLETE);
            deferResult.setResult(param);
            taskComplete.signalAll();
            return Response.<String>builder().code(200).msg("success").data("设置成功").build();
        } finally {
            lock.unlock();
        }
    }


    @Override
    public Response<String> preHandle(String token) {
        DeferResult<RunningParam> deferResult = deferredHolder.get(token);
        if (fieldUpdater.compareAndSet(deferResult, 0, 1)) {
            return Response.<String>builder().code(200).msg("success").build();
        } else {
            throw new RequestAlreadyException();
        }
    }

    @Override
    public DeviceRequestVo doHandle(String token) {
        final DeviceRequestForm deviceRequestForm = request.get(token);
        String username = deviceRequestForm.getUsername();
        String deviceId = deviceRequestForm.getDeviceId();
        final String param = getRun(deviceId);
        final List<TrainingResultVo> resultVos = resultService.personalResult(username, deviceId);
        DeviceRequestVo vo = new DeviceRequestVo();
        vo.setParams(param);
        vo.setResults(resultVos);
        return vo;
    }

    @Override
    public String getRun(String deviceId) {
        String param = deviceMapper.getRunParamByDeviceId(deviceId);
        return param;
    }

    @Override
    public boolean updateInfo(DeviceForm deviceForm) {
        Device device = new Device();
        BeanUtils.copyProperties(deviceForm, device);
        device.setEquipmentType(deviceForm.getDeviceType());
        device.setEquipmentState(deviceForm.getStatus());
        return deviceMapper.updateByPrimaryKeySelective(device) == 1;
    }

    @Override
    public List<DeviceVo> deviceList() {
        List<DeviceVo> devices = deviceMapper.getAllInfo();
        return devices;
    }

    @Override
    public Device getInfo(Short deviceId) {
        Device device = deviceMapper.selectByPrimaryKey(deviceId);
        return device;
    }

}
