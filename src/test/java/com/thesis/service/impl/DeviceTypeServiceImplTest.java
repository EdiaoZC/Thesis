package com.thesis.service.impl;

import com.thesis.SpringTest;
import com.thesis.common.model.DeviceType;
import com.thesis.common.model.vo.DeviceTypeVo;
import com.thesis.service.DeviceTypeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/29 16:53
 * @Description:
 */
public class DeviceTypeServiceImplTest extends SpringTest {

    @Autowired
    private DeviceTypeService deviceTypeService;

    @Test
    public void test() {
        final List<DeviceTypeVo> deviceTypeVos = deviceTypeService.deviceTypeList();
    }

}