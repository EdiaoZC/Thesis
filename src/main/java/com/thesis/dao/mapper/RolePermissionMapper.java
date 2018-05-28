package com.thesis.dao.mapper;

import com.thesis.common.model.Permission;
import com.thesis.common.model.RolePermission;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface RolePermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    RolePermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RolePermission record);

    int updateByPrimaryKey(RolePermission record);

    Set<Short> getPermissionIdByRoles(Collection<Byte> roleIds);

    List<Permission> getPermissionByRole(Byte id);

    void batchInsert(List<RolePermission> rolePermissions);


    int  deleteByRoleId(Byte id);

    int deleteByPermissionId(Integer id);
}