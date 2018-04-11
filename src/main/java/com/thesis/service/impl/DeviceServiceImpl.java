package com.thesis.service.impl;

import com.thesis.common.model.Device;
import com.thesis.dao.mapper.DeviceMapper;
import com.thesis.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/9 15:39
 * @Description:
 */
@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;


    @Override
    public int addDevice(Device device) {
        return deviceMapper.insertSelective(device);
    }

    @Override
    public int delDevice(Short deviceId) {
        return deviceMapper.deleteByPrimaryKey(deviceId);
    }
}
