package com.thesis.service;

import com.thesis.common.model.Response;
import com.thesis.common.model.UserRole;
import com.thesis.common.model.dto.UserRoleDto;
import com.thesis.common.model.form.UserForm;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/5/28 14:49
 * @Description:
 */
public interface UserRoleService {


    /**
     * 更新用户角色信息
     *
     * @param userRoleDto
     * @return
     */
    boolean updateUserRole(UserRoleDto userRoleDto);

    /**
     * 根据用户id删除数据
     *
     * @param userId
     * @return
     */
    boolean deleteByUserId(Long userId);

    /**
     * 更具角色id删除数据
     *
     * @param roleId
     * @return
     */
    boolean deleteByRoleId(@Param("") Byte roleId);
}
