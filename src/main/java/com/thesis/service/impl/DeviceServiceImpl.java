package com.thesis.service.impl;

import com.thesis.common.constants.App;
import com.thesis.common.exception.RequestAlreadyException;
import com.thesis.common.exception.TimeoutException;
import com.thesis.common.model.DeferResult;
import com.thesis.common.model.Device;
import com.thesis.common.model.Response;
import com.thesis.common.model.RunningParam;
import com.thesis.dao.mapper.DeviceMapper;
import com.thesis.service.DeviceService;
import com.thesis.service.WebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
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


    private ConcurrentHashMap<String, DeferResult<RunningParam>> deferredHolder;

    private Lock lock;

    private Condition taskComplete;

    private AtomicIntegerFieldUpdater<DeferResult> fieldUpdater;

    public DeviceServiceImpl() {
        this.deferredHolder = new ConcurrentHashMap<>();
        this.lock = new ReentrantLock();
        this.taskComplete = lock.newCondition();
        fieldUpdater = AtomicIntegerFieldUpdater.newUpdater(DeferResult.class, "status");
    }

    @Override
    public int addDevice(Device device) {
        return deviceMapper.insertSelective(device);
    }

    @Override
    public int delDevice(Short deviceId) {
        return deviceMapper.deleteByPrimaryKey(deviceId);
    }

    @Override
    public RunningParam requestDevice(String token) throws TimeoutException {
        log.debug("请求正在处理中");
        DeferResult<RunningParam> deferResult = new DeferResult<>();
        deferredHolder.put(token, deferResult);
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
        log.debug("请求处理完毕");
        return (RunningParam) deferResult.getResult();
    }


    @Override
    public Response<String> handleRequest(String token, RunningParam param) throws RequestAlreadyException {
        DeferResult<RunningParam> deferResult = deferredHolder.get(token);
        if (fieldUpdater.compareAndSet(deferResult, 0, 1)) {
            try {
                lock.lock();
                deferResult.setStatus(DeferResult.COMPLETE);
                deferResult.setResult(param);
                taskComplete.signalAll();
                return Response.<String>builder().code(200).msg("success").data("设置成功").build();
            } finally {
                lock.unlock();
            }
        } else {
            throw new RequestAlreadyException();
        }
    }

}
