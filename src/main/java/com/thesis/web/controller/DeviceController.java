package com.thesis.web.controller;

import com.alibaba.fastjson.JSON;
import com.thesis.common.model.Device;
import com.thesis.common.model.form.DeviceForm;
import com.thesis.common.model.vo.DeviceVo;
import com.thesis.service.DeviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.ResolutionSyntax;
import javax.validation.Valid;
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
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<DeviceVo>> deviceList() {
        List<DeviceVo> devices = deviceService.deviceList();
        return ResponseEntity.ok(devices);
    }

    @ApiOperation("查看设备信息")
    @GetMapping(value = "/{id:\\d+}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Device> deviceInfo(@PathVariable("id") Short id) {
        Device device = deviceService.getInfo(id);
        if (device != null) {
            return ResponseEntity.ok(device);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }


    @ApiOperation("查看设备信息")
    @GetMapping(value = "/{deviceId:\\w+}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> deviceInfo(@PathVariable("deviceId") String deviceId) {
        String param = deviceService.getRun(deviceId);
        return ResponseEntity.ok(param);
    }


    @ApiOperation("添加设备")
    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> addDevice(@Valid DeviceForm device, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(JSON.toJSONString(new ArrayList<>(errors.getFieldErrors())));
        }
        if (deviceService.addDevice(device)) {
            return ResponseEntity.ok("success");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail");
    }

    @ApiOperation("更新设备信息")
    @PutMapping
    public ResponseEntity<String> updateDeviceInfo(@Valid DeviceForm device, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(JSON.toJSONString(new ArrayList<>(errors.getFieldErrors())));
        }
        if (deviceService.updateInfo(device)) {
            return ResponseEntity.ok("success");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail");
    }


    @ApiOperation("删除设备")
    @DeleteMapping(value = "/{id:\\d+}")
    public ResponseEntity<String> delDevice(@PathVariable("id") Short id) {
        if (deviceService.delDevice(id)) {
            return ResponseEntity.ok("success");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail");
    }
}
