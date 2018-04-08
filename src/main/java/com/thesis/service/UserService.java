package com.thesis.service;

import com.thesis.common.model.User;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/8 14:22
 * @Description:
 */
public interface UserService {


    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    User loadUserByName(String username);

    /**
     * 添加用户信息
     * @param user
     * @return
     */
    int insertUser(User user);
}
