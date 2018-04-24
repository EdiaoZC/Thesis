package com.thesis.web.controller;

import com.thesis.common.model.Response;
import com.thesis.common.model.RunningParam;
import com.thesis.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/24 11:10
 * @Description:
 */
@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DeviceService deviceService;


    @PostMapping(value = "/device", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response<String> handleRequest(String token, RunningParam param) {
        return deviceService.handleRequest(token, param);
    }
}
