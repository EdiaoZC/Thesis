package com.thesis.service;

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
     * 重新获取 token
     *
     * @param token
     */
    void refreshToken(String token);

    /**
     * 存储 token
     * @param token
     * @param userDetails
     */
    void saveToken(String token, UserDetails userDetails);
}
