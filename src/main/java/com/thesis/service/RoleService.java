package com.thesis.service;

import com.thesis.common.model.Role;
import com.thesis.common.model.dto.RolePermissionDto;
import com.thesis.common.model.form.RoleForm;
import com.thesis.common.model.vo.RoleVo;
import org.springframework.http.ResponseEntity;

import java.util.List;
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
    boolean addRole(RoleForm role);

    /**
     * 删除角色信息
     * @param role
     * @return
     */
    int delRole(Role role);

    /**
     * 返回角色列表
     * @return
     */
    List<RoleVo> getRoles(Byte id);
}
