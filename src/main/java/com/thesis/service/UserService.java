package com.thesis.service;

import com.thesis.common.model.User;
import com.thesis.common.model.form.UserForm;
import com.thesis.common.model.vo.UserVo;

import java.util.List;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/8 14:22
 * @Description:
 */
public interface UserService {


    /**
     * 根据用户名获取用户信息
     *
     * @param username
     * @return
     */
    User loadUserByName(String username);

    /**
     * 添加用户信息
     *
     * @param user
     * @return
     */
    int register(UserForm user);


    /**
     * 返回所有用户信息
     *
     * @return
     */
    List<UserVo> userList();


    /**
     * 根据用户 id 删除用户信息
     *
     * @param id 用户id
     * @return 是否删除成功
     */
    boolean delUserById(Long id);

    /**
     * 根据用户 id 更新用户信息
     *
     * @param user 用户信息
     * @return 是否更新成功
     */
    boolean updateUserById(UserForm user, String token);

    /**
     * 更具用户 id 获取用户详细信息
     *
     * @param id 用户 id
     * @return 用户信息
     */
    User getInfoById(Long id);
}
