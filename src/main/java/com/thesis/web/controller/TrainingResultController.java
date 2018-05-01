package com.thesis.web.controller;

import com.thesis.common.model.Permission;
import com.thesis.common.model.Response;
import com.thesis.common.model.Role;
import com.thesis.common.model.TrainingResult;
import com.thesis.common.model.form.TrainingResultForm;
import com.thesis.common.model.vo.TrainingResultVo;
import com.thesis.dao.mapper.TrainingResultMapper;
import com.thesis.service.TrainingResultService;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/11 11:24
 * @Description:
 */
@RestController
@RequestMapping("/training_result")
public class TrainingResultController {

    @Autowired
    private TrainingResultService resultService;


    @ApiOperation("训练结果")
    @GetMapping
    public ResponseEntity<List<TrainingResultVo>> trainingResults() {
        final List<TrainingResultVo> resultVos = resultService.resultList();
        return ResponseEntity.ok(resultVos);
    }

    @ApiOperation("训练结果")
    @GetMapping("/{name:\\w+}/{deviceId:\\w+}")
    public ResponseEntity<List<TrainingResultVo>> personalResult(@PathVariable("name") String name
            , @PathVariable("deviceId") String deviceId) {
        final List<TrainingResultVo> resultVos = resultService.personalResult(name,deviceId);
        return ResponseEntity.ok(resultVos);
    }


    @PostMapping()
    @ApiOperation("添加训练信息")
    public ResponseEntity<String> addTrainingResult(@Valid TrainingResultForm resultForm, Errors errors) {
        if (errors.hasErrors()) {

        }
        if (resultService.addTrainingResult(resultForm)) {
            return ResponseEntity.ok("success");
        }
        return ResponseEntity.ok("fail");
    }


}
