package com.thesis.service.impl;

import com.thesis.common.model.Permission;
import com.thesis.dao.mapper.PermissionMapper;
import com.thesis.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public Set<String> getUrlsByUserName(String username) {
        return permissionMapper.getUrlByUsername(username);
    }
}
