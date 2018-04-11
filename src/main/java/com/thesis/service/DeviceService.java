package com.thesis.service;

import com.thesis.common.model.Device;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/9 14:03
 * @Description:
 */
public interface DeviceService {


    /**
     * 添加设备
     *
     * @return
     */
    int addDevice(Device device);

    /**
     * 删除设备
     *
     * @return
     */
    int delDevice(Short deviceId);

}
