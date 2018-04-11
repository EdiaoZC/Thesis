package com.thesis.service.impl;

import com.thesis.common.model.Role;
import com.thesis.common.model.RolePermission;
import com.thesis.common.model.User;
import com.thesis.common.model.UserRole;
import com.thesis.dao.mapper.*;
import com.thesis.service.RoleService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/11 14:38
 * @Description:
 */
@Service
public class RoleServiceImpl implements RoleService {




    @Override
    public Set<String> getRoleByUsername(String username) {
        return null;
    }

    @Override
    public int addRole(Role role) {
        return 0;
    }

    @Override
    public int delRole(Role role) {
        return 0;
    }
}
