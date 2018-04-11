package com.thesis.web.controller;

import com.thesis.common.model.Device;
import com.thesis.service.DeviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/9 16:46
 * @Description:
 */
@Api("设备管理")
@Controller
@RequestMapping("/devices")
public class DeviceController {


    @Autowired
    private DeviceService deviceService;


    @ApiOperation("设备列表")
    @GetMapping("/{id:\\d+}")
    public ResponseEntity<String> deviceList(@PathVariable("id") Integer id) {
        return ResponseEntity.ok("设备列表");
    }

    @ApiOperation("查看设备信息")
    @GetMapping
    public ResponseEntity<String> deviceInfo() {
        return ResponseEntity.ok("查看设备信息");
    }


    @ApiOperation("添加设备")
    @PostMapping
    public ResponseEntity<String> addDevice(Device device) {
        return ResponseEntity.ok("添加设备");
    }

    @ApiOperation("更新设备信息")
    @PutMapping
    public ResponseEntity<String> updateDeviceInfo() {
        return ResponseEntity.ok("更新设备信息");
    }


    @ApiOperation("删除设备")
    @DeleteMapping
    public ResponseEntity<String> delDevice() {
        return ResponseEntity.ok("删除设备");
    }
}
