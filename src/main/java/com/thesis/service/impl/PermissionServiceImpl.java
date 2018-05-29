package com.thesis.service.impl;

import com.thesis.common.model.Permission;
import com.thesis.common.model.form.PermissionForm;
import com.thesis.dao.mapper.PermissionMapper;
import com.thesis.dao.mapper.RolePermissionMapper;
import com.thesis.service.PermissionService;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/11 14:39
 * @Description:
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public Set<String> getUrlsByUserName(String username) {
        return permissionMapper.getUrlByUsername(username);
    }

    @Override
    public List<Permission> permissionList() {
        return permissionMapper.permissionList();
    }

    @Override
    public Permission permissionInfo(Integer id) {
        return permissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean addPermission(PermissionForm permissionForm) {
        Permission permission = new Permission();
        BeanUtils.copyProperties(permissionForm, permission);
        return permissionMapper.insertSelective(permission) == 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delPermission(Integer id) {
        rolePermissionMapper.deleteByPermissionId(id);
        return permissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public boolean updatePermission(PermissionForm permissionForm) {
        Permission permission = new Permission();
        BeanUtils.copyProperties(permissionForm, permission);
        return permissionMapper.updateByPrimaryKey(permission) == 1;
    }
}
