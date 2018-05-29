package com.thesis.service.impl;

import com.thesis.common.exception.RoleException;
import com.thesis.common.model.dto.UserRoleDto;
import com.thesis.dao.mapper.UserRoleMapper;
import com.thesis.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @Author: ZcEdiaos
 * @Date: 2018/5/28 14:55
 * @Description:
 */
@Service
@Slf4j
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateUserRole(UserRoleDto userRoleDto) throws RoleException {
        final List<Byte> roleIds = userRoleDto.getRoleIds();
        log.info("roleIds对象：{}", roleIds);
        if (roleIds.contains(com.thesis.common.constants.Role.PATIENTS_TAG) && roleIds.size() != 1) {
            return false;
        }
        userRoleService.deleteByUserId(userRoleDto.getUserId());
        userRoleMapper.batchInsert(userRoleDto);
        return true;
    }

    @Override
    public boolean deleteByUserId(Long userId) {
        return userRoleMapper.deleteByUserId(userId) >= 1;
    }

    @Override
    public boolean deleteByRoleId(Byte roleId) {
        return userRoleMapper.deleteByRoleId(roleId) >= 1;
    }
}
