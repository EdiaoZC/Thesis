package com.thesis.web.controller;

import com.thesis.common.model.Response;
import com.thesis.common.model.RunningParam;
import com.thesis.common.model.form.DeviceRequestForm;
import com.thesis.service.DeviceService;
import com.thesis.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/24 10:01
 * @Description:
 */
@RestController
@RequestMapping("/patients")
public class PatientController {


    @Autowired
    private DeviceService deviceService;

    @RequestMapping(value = "/device", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Callable<ResponseEntity<RunningParam>> deviceRequest(String token, DeviceRequestForm form) {
        return new Callable<ResponseEntity<RunningParam>>() {
            @Override
            public ResponseEntity<RunningParam> call() throws Exception {
                return ResponseEntity.ok(deviceService.requestDevice(token,form));
            }
        };
    }


}
