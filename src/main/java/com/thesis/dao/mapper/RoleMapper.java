package com.thesis.dao.mapper;

import com.thesis.common.model.Role;
import com.thesis.common.model.dto.RolePermissionDto;
import com.thesis.common.model.vo.RoleVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface RoleMapper {
    int deleteByPrimaryKey(Byte id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Byte id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    Set<Role> getRoleByUsername(String username);

    List<RolePermissionDto> getRoles(@Param("id") Byte id);
}