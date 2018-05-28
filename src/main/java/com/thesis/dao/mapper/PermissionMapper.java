package com.thesis.dao.mapper;

import com.thesis.common.model.Permission;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    Set<String> getUrlByUsername(String username);

    List<Permission> permissionList();
}