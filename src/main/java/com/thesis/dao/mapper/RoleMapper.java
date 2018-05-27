package com.thesis.dao.mapper;

import com.thesis.common.model.Role;
import com.thesis.common.model.vo.RoleVo;
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

    Set<String> getRoleByUsername(String username);

    List<RoleVo> getRoles(Byte id);
}