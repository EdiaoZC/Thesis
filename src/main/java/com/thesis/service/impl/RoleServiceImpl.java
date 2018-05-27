package com.thesis.service.impl;

import com.thesis.common.model.Role;
import com.thesis.common.model.RolePermission;
import com.thesis.common.model.User;
import com.thesis.common.model.UserRole;
import com.thesis.common.model.dto.RolePermissionDto;
import com.thesis.common.model.form.RoleForm;
import com.thesis.common.model.vo.RoleVo;
import com.thesis.dao.mapper.*;
import com.thesis.service.RoleService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/11 14:38
 * @Description:
 */
@Service
public class RoleServiceImpl implements RoleService {


    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public Set<String> getRoleByUsername(String username) {
        return roleMapper.getRoleByUsername(username);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addRole(RoleForm roleForm) {
        Role role = new Role();
        BeanUtils.copyProperties(roleForm, role);
        RolePermissionDto dto = new RolePermissionDto();
        dto.setRoleId(role.getId());
    }

    @Override
    public int delRole(Role role) {
        return 0;
    }

    @Override
    public List<RoleVo> getRoles(Byte id) {
        List<RoleVo> roles = roleMapper.getRoles(id);
        return roles;
    }
}
