package com.thesis.service.impl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.thesis.common.constants.SecurityProperties;
import com.thesis.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/16 15:53
 * @Description:
 */
@Service
@Slf4j
public class DefaultTokenServiceImpl implements TokenService {


    @Autowired
    private SecurityProperties security;

    private LoadingCache<String, UserDetails> tokenCache = CacheBuilder.newBuilder()
            .maximumSize(10000)
            .expireAfterAccess(30, TimeUnit.MINUTES)
            .build(new CacheLoader<String, UserDetails>() {
                @Override
                public UserDetails load(String key) throws Exception {
                    return security.getUser();
                }
            });


    private ConcurrentHashMap<Long, String> userToken = new ConcurrentHashMap<>();

    @Override
    public UserDetails getUserInfoFromToken(String token) throws ExecutionException {
        UserDetails user = tokenCache.get(token);
        log.info("user对象是:{}", user);
        return user;
    }

    @Override
    public boolean isValidToken(String token) throws ExecutionException {
        return tokenCache.get(token) != null;
    }

    @Override
    public void refreshToken(com.thesis.common.model.User user) throws ExecutionException {
        String token = userToken.get(user.getId());
        if (token == null) {
            return;
        }
        UserDetails details = tokenCache.get(token);
        boolean enabled = (user.getStatus() & 8) != 8;
        boolean accountNonExpired = (user.getStatus() & 4) != 4;
        boolean credentialsNonExpired = (user.getStatus() & 2) != 2;
        boolean accountNonLocked = (user.getStatus() & 1) != 1;
        log.info("用户状态:{}-{}-{}-{}", enabled, accountNonExpired, credentialsNonExpired, accountNonLocked);
        final User newUser = new User(user.getUsername(), details.getPassword(), enabled, accountNonExpired
                , credentialsNonExpired, accountNonLocked, details.getAuthorities());
        log.info("newUser对象是:{}", newUser);
        tokenCache.put(token, newUser);
    }

    @Override
    public void saveToken(String token, Long id, UserDetails userDetails) {
        userToken.put(id, token);
        tokenCache.put(token, userDetails);
    }
}
