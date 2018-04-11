package com.thesis.service;

import com.thesis.common.model.Role;

import java.util.Set;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/11 14:38
 * @Description:
 */
public interface RoleService {

    /**
     * 通过用户名获取到用户的角色
     *
     * @param username 用户名
     * @return 用户拥有的角色
     */
    Set<String> getRoleByUsername(String username);

    /**
     * 添加角色信息
     * @param role 角色
     * @return 添加是否成功
     */
    int addRole(Role role);

    /**
     * 删除角色信息
     * @param role
     * @return
     */
    int delRole(Role role);

}
