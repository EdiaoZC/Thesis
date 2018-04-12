package com.thesis.service;

import com.thesis.common.exception.UsernameRepeatException;
import com.thesis.common.model.User;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/12 15:09
 * @Description:
 */
public interface AuthService {


    /**
     * 用户注册功能
     * @param user 用户
     * @return 成功后返回用户信息，失败后返回空
     * @throws UsernameRepeatException
     */
    User register(User user) throws UsernameRepeatException;

    /**
     * 用户登录功能
     * @param username
     * @param password
     * @return
     */
    String login(String username, String password);

}
