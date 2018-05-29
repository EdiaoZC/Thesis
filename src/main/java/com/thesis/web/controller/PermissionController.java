package com.thesis.web.controller;

import com.thesis.common.constants.Error;
import com.thesis.common.model.Permission;
import com.thesis.common.model.Response;
import com.thesis.common.model.Role;
import com.thesis.common.model.form.PermissionForm;
import com.thesis.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @ApiOperation("查看权限")
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response<List<Permission>> permissions() {
        final List<Permission> permissions = permissionService.permissionList();
        return Response.<List<Permission>>builder().code(200)
                .msg("success").data(permissions).build();
    }


    @GetMapping(value = "/{id:\\d+}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation("查看权限详细信息")
    public Response<Permission> roleInfo(@PathVariable("id") Integer id) {
        final Permission permission = permissionService.permissionInfo(id);
        return Response.<Permission>builder().code(200)
                .msg("success").data(permission).build();
    }


    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation("添加权限信息")
    public Response addPermission(@Valid PermissionForm permission, Errors errors) {
        final boolean result = permissionService.addPermission(permission);
        if (result) {
            return Response.builder()
                    .code(200).msg("success").build();
        } else {
            return Response.builder()
                    .code(400).msg("fail").build();
        }
    }


    @PutMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation("更新权限信息")
    public Response updatePermission(@Valid PermissionForm permission, Errors errors) {
        final boolean result = permissionService.updatePermission(permission);
        if (result) {
            return Response.builder()
                    .code(200).msg("success").build();
        } else {
            return Response.builder()
                    .code(400).msg("fail").build();
        }
    }


    @DeleteMapping(value = "/{id}")
    @ApiOperation("删除权限信息")
    public Response
    delRole(@PathVariable("id") Integer id) {
        final int result = permissionService.delPermission(id);
        if (result >= 1) {
            return Response.builder()
                    .code(200).msg("success").build();
        } else {
            return Response.builder()
                    .code(400).msg("fail").build();
        }
    }

}
