package com.thesis.web.controller;

import com.thesis.common.model.Response;
import com.thesis.common.model.form.DeviceTypeForm;
import com.thesis.common.model.vo.DeviceTypeVo;
import com.thesis.service.DeviceTypeService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/29 16:33
 * @Description:
 */
@RestController
@RequestMapping("/deviceType")
@Slf4j
public class DeviceTypeController {

    @Autowired
    private DeviceTypeService deviceTypeService;

    @ApiOperation("获取设备类型")
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Response<Object>> deviceTypeVoList() {
        List<DeviceTypeVo> deviceTypeList = deviceTypeService.deviceTypeList();
        final Response<Object> result = Response.builder().msg("success").data(deviceTypeList).build();
        return ResponseEntity.ok(result);
    }

    @ApiOperation("获取设备类型")
    @GetMapping(value = "/{id:\\d+}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<DeviceTypeVo> deviceTypeInfo(@PathVariable("id") Byte id) {
        DeviceTypeVo deviceTypeVo = deviceTypeService.deviceType(id);
        return ResponseEntity.ok(deviceTypeVo);
    }

    @ApiOperation("新增设备类型")
    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> addDeviceType(DeviceTypeForm deviceTypeForm) {
        log.info("deviceTypeForm对象是:{}", deviceTypeForm);
        deviceTypeService.addType(deviceTypeForm);
        return ResponseEntity.ok("success");
    }

    @ApiOperation("获取设备类型")
    @PutMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> updateDeviceType(DeviceTypeForm deviceTypeForm) {
        deviceTypeService.updateInfo(deviceTypeForm);
        return ResponseEntity.ok("success");
    }

    @ApiOperation("删除设备类型")
    @DeleteMapping(value = "/{id:\\d+}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> delDeviceType(@PathVariable("id") Byte id) {
        deviceTypeService.delType(id);
        return ResponseEntity.ok("success");
    }
}
