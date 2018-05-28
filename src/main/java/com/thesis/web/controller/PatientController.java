package com.thesis.web.controller;

import com.thesis.common.model.Response;
import com.thesis.common.model.RunningParam;
import com.thesis.common.model.form.DeviceRequestForm;
import com.thesis.common.model.form.TrainingResultForm;
import com.thesis.service.DeviceService;
import com.thesis.service.TrainingResultService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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

    @Autowired
    private TrainingResultService trainingResultService;

    @ApiOperation("申请使用设备")
    @PostMapping(value = "/device", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Callable<ResponseEntity<List<RunningParam>>> deviceRequest(String token, DeviceRequestForm form) {
        return new Callable<ResponseEntity<List<RunningParam>>>() {
            @Override
            public ResponseEntity<List<RunningParam>> call() throws Exception {
                return ResponseEntity.ok(deviceService.requestDevice(token, form));
            }
        };
    }

    @ApiOperation("取消申请")
    @GetMapping(value = "/cancel", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Response<String>> cancelRequest(String token, String deviceId) {
        return ResponseEntity.ok(deviceService.cancelRequest(token, deviceId));
    }


    @ApiOperation("提交训练结果")
    @PostMapping(value = "/trainingResult", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response<String> trainingResult(TrainingResultForm trainingResultForm) {
        if (trainingResultService.addTrainingResult(trainingResultForm)) {
            return Response.<String>builder().code(200).msg("success").data("提交成功").build();
        }else {
            return Response.<String>builder().code(410).msg("fail").data("提交失败").build();
        }
    }
}
