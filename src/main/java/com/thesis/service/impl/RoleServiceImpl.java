package com.thesis.service.impl;

import com.thesis.common.model.*;
import com.thesis.common.model.dto.RolePermissionDto;
import com.thesis.common.model.form.RoleForm;
import com.thesis.common.model.vo.RoleVo;
import com.thesis.dao.mapper.*;
import com.thesis.service.RoleService;
import com.thesis.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class RoleServiceImpl implements RoleService {


    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    @Autowired
    private UserRoleService userRoleService;

    @Override
    public Set<Role> getRoleByUsername(String username) {
        return roleMapper.getRoleByUsername(username);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addRole(RoleForm roleForm) {
        Role role = new Role();
        BeanUtils.copyProperties(roleForm, role);
        roleMapper.insertSelective(role);
        List<RolePermission> rolePermissions = new ArrayList<>();
        for (Integer permissionId : roleForm.getPermissions()) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(Integer.valueOf(role.getId()));
            rolePermission.setPermissionId(permissionId);
            rolePermissions.add(rolePermission);
        }
        rolePermissionMapper.batchInsert(rolePermissions);
        return true;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delRole(Byte roleId) {
        userRoleService.deleteByRoleId(roleId);
        return roleMapper.deleteByPrimaryKey(roleId);
    }

    @Override
    public List<RoleVo> getRoles(Byte id) {
        List<RolePermissionDto> roles = roleMapper.getRoles(id);
        List<RoleVo> roleVos = new ArrayList<>();
        for (RolePermissionDto role : roles) {
            RoleVo roleVo = new RoleVo();
            roleVo.setId(role.getId());
            roleVo.setName(role.getName());
            roleVo.setDescr(role.getDescr());
            roleVo.setPermissions(role.getPermissions());
            roleVos.add(roleVo);
        }
        return roleVos;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateRole(RoleForm roleForm) {
        rolePermissionMapper.deleteByRoleId(roleForm.getId());
        Role role = new Role();
        BeanUtils.copyProperties(roleForm, role);
        roleMapper.updateByPrimaryKeySelective(role);
        List<RolePermission> rolePermissions = new ArrayList<>();
        for (Integer id : roleForm.getPermissions()) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(Integer.valueOf(role.getId()));
            rolePermission.setPermissionId(id);
            rolePermissions.add(rolePermission);
        }
        rolePermissionMapper.batchInsert(rolePermissions);
        return true;

    }
}
