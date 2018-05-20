package com.thesis.service.impl;

import com.thesis.common.constants.Status;
import com.thesis.common.exception.RequestAlreadyException;
import com.thesis.common.exception.TimeoutException;
import com.thesis.common.model.DeferResult;
import com.thesis.common.model.Device;
import com.thesis.common.model.Response;
import com.thesis.common.model.RunningParam;
import com.thesis.common.model.form.DeviceForm;
import com.thesis.common.model.form.DeviceRequestForm;
import com.thesis.common.model.form.RequestForm;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

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

    private ConcurrentHashMap<String, DeviceRequestForm> preHandle;

    private ConcurrentHashMap<String, DeviceRequestForm> doHandle;
    @Autowired
    private TrainingResultService resultService;

    private Long timeOut;

    private Response<String> timeOutObject;

    private ConcurrentHashMap<String, DeferResult<List<RunningParam>>> deferredHolder;

    private Lock lock;

    private Condition taskComplete;

    private AtomicIntegerFieldUpdater<DeferResult> fieldUpdater;

    public DeviceServiceImpl() {
        this.deferredHolder = new ConcurrentHashMap<>();
        this.lock = new ReentrantLock();
        this.taskComplete = lock.newCondition();
        fieldUpdater = AtomicIntegerFieldUpdater.newUpdater(DeferResult.class, "status");
        this.preHandle = new ConcurrentHashMap<>();
        this.doHandle = new ConcurrentHashMap<>();
        this.timeOut = 100000L;
        this.timeOutObject = Response.<String>builder().code(410).msg("fail").data("超时").build();
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
    public List<RunningParam> requestDevice(String token, DeviceRequestForm form) throws TimeoutException {
        //设置超时
        DeferResult<List<RunningParam>> deferResult = new DeferResult<>(timeOut, timeOutObject);
        deferredHolder.put(token, deferResult);
        preHandle.put(token, form);
        final String deviceId = form.getDeviceId();
        deviceMapper.updateStatus(deviceId, Status.USING);
        webSocketService.sendMessage(token);
        try {
            lock.lock();
            while (deferResult.getStatus() != DeferResult.COMPLETE) {
                if (deferResult.getStatus() == DeferResult.CANCEL) {
                    deferredHolder.remove(token);
                    break;
                }
                taskComplete.await();
            }
        } catch (InterruptedException e) {
            log.error("出现错误:{}", e);
        } finally {
            lock.unlock();
        }
        final List<RunningParam> result = (List<RunningParam>) deferResult.getResult();
        deferredHolder.remove(token);
        return result;
    }


    @Override
    public Response<String> handleRequest(RequestForm request) throws RequestAlreadyException {
        DeferResult<List<RunningParam>> deferResult = deferredHolder.get(request.getToken());
        try {
            lock.lock();
            deferResult.setStatus(DeferResult.COMPLETE);
            deferResult.setResult(request.getRun());
            taskComplete.signalAll();
            return Response.<String>builder().code(200).msg("success").data("设置成功").build();
        } finally {
            lock.unlock();
        }
    }


    @Override
    public Response<String> preHandle(String token) {
        DeferResult<List<RunningParam>> deferResult = deferredHolder.get(token);
        if (fieldUpdater.compareAndSet(deferResult, 0, 1)) {
            final DeviceRequestForm deviceRequestForm = preHandle.get(token);
            preHandle.remove(token);
            doHandle.put(token, deviceRequestForm);
            return Response.<String>builder().code(200).msg("success").build();
        } else {
            throw new RequestAlreadyException();
        }
    }

    @Override
    public DeviceRequestVo doHandle(String token) {
        final DeviceRequestForm deviceRequestForm = preHandle.get(token);
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
    public Response<Object> unHandleRequest() {
        if (preHandle == null || preHandle.size() == 0) {
            return Response.builder().code(402).msg("没有请求").build();
        }
        return Response.builder().code(200).msg("success").data(preHandle.keySet()).build();
    }

    @Override
    public Response<String> cancelRequest(String token, String deviceId) {
        deferredHolder.get(token).setStatus(DeferResult.CANCEL);
        deviceMapper.updateStatus(deviceId, Status.NORMAL);
        preHandle.remove(token);
        doHandle.remove(token);
        return Response.<String>builder().code(200).msg("success").build();
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
