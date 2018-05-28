package com.thesis.web.controller;

import com.thesis.common.model.Response;
import com.thesis.common.model.Role;
import com.thesis.common.model.form.RoleForm;
import com.thesis.common.model.vo.RoleVo;
import com.thesis.service.RoleService;
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
 * @Date: 2018/4/12 13:50
 * @Description:
 */
@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation("查看角色")
    public Response<List<RoleVo>> getRoles() {
        final List<RoleVo> roles = roleService.getRoles(null);
        return Response.<List<RoleVo>>builder().code(200)
                .msg("success").data(roles).build();
    }

    @GetMapping(value = "/{id:\\d+}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation("查看角色详细信息")
    public Response<RoleVo> roleInfo(@PathVariable("id") Byte id) {
        List<RoleVo> roles = roleService.getRoles(id);
        return Response.<RoleVo>builder().code(200)
                .msg("success").data(roles.get(0)).build();
    }


    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation("添加角色信息")
    public Response addRole(@Valid RoleForm role, Errors errors) {
        final boolean result = roleService.addRole(role);
        if (result) {
            return Response.builder()
                    .code(200).msg("success").build();
        } else {
            return Response.builder()
                    .code(400).msg("fail").build();
        }
    }


    @PutMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation("更新角色信息")
    public Response updateRole(RoleForm role) {
        final boolean result = roleService.updateRole(role);
        if (result) {
            return Response.builder()
                    .code(200).msg("success").build();
        } else {
            return Response.builder()
                    .code(400).msg("fail").build();
        }
    }


    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation("删除角色信息")
    public Response delRole(@PathVariable("id") Byte roleId) {
        final int result = roleService.delRole(roleId);
        if (result >= 1) {
            return Response.builder()
                    .code(200).msg("success").build();
        } else {
            return Response.builder()
                    .code(400).msg("fail").build();
        }
    }


}
