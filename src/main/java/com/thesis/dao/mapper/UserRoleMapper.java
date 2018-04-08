package com.thesis.dao.mapper;

import com.thesis.common.model.UserRole;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Byte id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Byte id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
}