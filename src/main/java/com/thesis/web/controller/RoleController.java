package com.thesis.web.controller;

import com.thesis.common.model.Response;
import com.thesis.common.model.Role;
import com.thesis.common.model.form.RoleForm;
import com.thesis.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
@Controller
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping()
    @ApiOperation("查看角色")
    public ResponseEntity<List<Role>> getRoles() {
        return roleService.getRoles();
    }

    @GetMapping("/{id:\\d+}")
    @ApiOperation("查看角色详细信息")
    public ResponseEntity<Role> roleInfo(@PathVariable("id") Integer id) {
        return roleService.getRoles(id);
    }


    @PostMapping()
    @ApiOperation("添加角色信息")
    public ResponseEntity<Role> addRole(@Valid RoleForm role, Errors errors) {
        return roleService.addRole(role);
    }


    @PutMapping()
    @ApiOperation("更新角色信息")
    public ResponseEntity<String> updateRole(Role role) {
        return ResponseEntity.ok(null);
    }


    @DeleteMapping()
    @ApiOperation("删除角色信息")
    public ResponseEntity<String> delRole(Role role) {
        return ResponseEntity.ok(null);
    }


}
