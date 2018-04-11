package com.thesis.service.impl;

import com.thesis.SpringTest;
import com.thesis.common.model.Permission;
import com.thesis.service.PermissionService;
import com.thesis.service.RoleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;

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

    @Test
    public void RoleTest() {
        Set<String> admin = permissionService.getUrlsByUserName("admin");
        for (String s : admin) {
            System.out.println(s);
        }
    }
}