package com.thesis.service.impl;

import com.thesis.SpringTest;
import com.thesis.common.model.Permission;
import com.thesis.common.model.form.RoleForm;
import com.thesis.service.PermissionService;
import com.thesis.service.RoleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/11 15:03
 * @Description:
 */
public class RoleServiceImplTest extends SpringTest {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RoleService roleService;

    @Test
    public void RoleTest() {
        Set<String> admin = permissionService.getUrlsByUserName("admin");
        for (String s : admin) {
            System.out.println(s);
        }
    }

    @Test
    public void getRoleByUsername() {
    }

    @Test
    public void addRole() {
        RoleForm roleForm = new RoleForm();
        roleForm.setName("角色管理员");
        roleForm.setDescr("负责系统的角色信息管理");
        roleForm.setPermissions(Arrays.asList(1, 3, 4));
        roleService.addRole(roleForm);
    }

    @Test
    public void delRole() {
        Byte id = 5;
        roleService.delRole(id);
    }

    @Test
    public void getRoles() {
        Byte id = 3;
        roleService.getRoles(id);
    }
}