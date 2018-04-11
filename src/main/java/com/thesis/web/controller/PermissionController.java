package com.thesis.web.controller;

import com.thesis.common.model.Permission;
import com.thesis.common.model.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/11 11:34
 * @Description:
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {


    @ApiOperation("查看权限")
    @GetMapping(value = "/{userId:\\d+}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Response<List<Permission>>> permissions(@PathVariable("userId") Integer userId) {
        return ResponseEntity.ok(null);
    }

}
