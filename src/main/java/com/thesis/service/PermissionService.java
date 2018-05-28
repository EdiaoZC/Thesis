package com.thesis.service;

import com.thesis.common.model.Permission;
import com.thesis.common.model.form.PermissionForm;

import java.util.List;
import java.util.Set;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/11 14:38
 * @Description:
 */
public interface PermissionService {

    /**
     * 根据用户名获取他可以访问的路径
     *
     * @param username
     * @return
     */
    Set<String> getUrlsByUserName(String username);

    List<Permission> permissionList();

    Permission permissionInfo(Integer id);

    /**
     * 添加权限
     *
     * @param permissionForm
     * @return
     */
    boolean addPermission(PermissionForm permissionForm);

    /**
     * 删除权限
     *
     * @param id
     * @return
     */
    int delPermission(Integer id);

    /**
     * 更新权限
     *
     * @param permissionForm
     * @return
     */
    boolean updatePermission(PermissionForm permissionForm);

}
