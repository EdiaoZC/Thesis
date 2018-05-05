package com.thesis.web.controller;

import com.thesis.common.model.Response;
import com.thesis.common.model.RunningParam;
import com.thesis.common.model.form.DeviceRequestForm;
import com.thesis.common.model.form.RequestForm;
import com.thesis.common.model.vo.DeviceRequestVo;
import com.thesis.service.DeviceService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.PriorityQueue;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/24 11:10
 * @Description:
 */
@RestController
@RequestMapping("/doctor/device")
public class DoctorController {

    @Autowired
    private DeviceService deviceService;


    @GetMapping
    public Response<String> preHandle(String token) {
        return deviceService.preHandle(token);
    }


    @GetMapping("/token/{token}")
    public Response<DeviceRequestVo> doHandle(@PathVariable("token") String token) {
        final DeviceRequestVo deviceRequestVo = deviceService.doHandle(token);
        return Response.<DeviceRequestVo>builder().code(200).data(deviceRequestVo).build();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response<String> postHandle(RequestForm request) {
        return deviceService.handleRequest(request);
    }
}
