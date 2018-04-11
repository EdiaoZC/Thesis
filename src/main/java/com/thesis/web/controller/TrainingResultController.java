package com.thesis.web.controller;

import com.thesis.common.model.Response;
import com.thesis.common.model.TrainingResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/11 11:24
 * @Description:
 */
@Controller
@RequestMapping("/training_result")
public class TrainingResultController {

    @ApiOperation("训练结果")
    @GetMapping("/{id:\\d+}")
    public ResponseEntity<Response<List<TrainingResult>>> trainingResults(@PathVariable("id")Integer id ) {
        return ResponseEntity.ok(null);
    }


}
