package com.thesis.dao.mapper;

import com.thesis.common.model.UserRole;
import com.thesis.common.model.dto.UserRoleDto;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Byte id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Byte id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    Set<Byte> selectByUserId(Long userId);

    boolean batchInsert(@Param("user") UserRoleDto userRoleDto);

    int deleteByUserId(Long userId);

    int deleteByRoleId(Byte roleId);

}