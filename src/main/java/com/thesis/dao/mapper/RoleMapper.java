package com.thesis.dao.mapper;

import com.thesis.common.model.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(Byte id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Byte id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}