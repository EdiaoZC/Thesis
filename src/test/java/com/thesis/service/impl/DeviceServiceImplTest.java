package com.thesis.service.impl;

import com.thesis.SpringTest;
import com.thesis.common.model.vo.DeviceVo;
import com.thesis.service.DeviceService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/27 20:43
 * @Description:
 */
public class DeviceServiceImplTest extends SpringTest {

    @Autowired
    private DeviceService deviceService;

    @Test
    public void getDeviceVo() {
        List<DeviceVo> deviceVos = deviceService.deviceList();
        System.out.println(deviceVos);
    }

}