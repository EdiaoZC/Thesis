package com.thesis.web.controller;

import com.thesis.common.model.Permission;
import com.thesis.common.model.Response;
import com.thesis.common.model.Role;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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


    @GetMapping("/{id:\\d+}")
    @ApiOperation("查看权限详细信息")
    public ResponseEntity<Role> roleInfo(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(null);
    }


    @PostMapping()
    @ApiOperation("添加权限信息")
    public ResponseEntity<Role> addRole(@Valid Permission permission, Errors errors) {
        return ResponseEntity.ok(null);
    }


    @PutMapping()
    @ApiOperation("更新权限信息")
    public ResponseEntity<String> updateRole(Role role) {
        return ResponseEntity.ok(null);
    }


    @DeleteMapping()
    @ApiOperation("删除权限信息")
    public ResponseEntity<String> delRole(Role role) {
        return ResponseEntity.ok(null);
    }

}
