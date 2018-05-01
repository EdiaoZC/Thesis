package com.thesis.service;

import com.thesis.common.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.concurrent.ExecutionException;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/16 15:49
 * @Description:
 */
public interface TokenService {

    /**
     * 从存储的 token 中获取用户信息
     *
     * @param token
     * @return
     */
    UserDetails getUserInfoFromToken(String token) throws ExecutionException;


    /**
     * 校验 token 是否过期
     *
     * @param token
     * @return
     */
    boolean isValidToken(String token) throws ExecutionException;


    /**
     * 更新 token 中存储的的用户信息
     *
     * @param user 用户信息
     * @throws ExecutionException
     */
    void refreshToken(User user) throws ExecutionException;

    /**
     * 存储 token
     *
     * @param token
     * @param id
     * @param userDetails
     */
    void saveToken(String token, Long id, UserDetails userDetails);
}
