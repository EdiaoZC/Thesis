package com.thesis.service.impl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.thesis.common.constants.SecurityProperties;
import com.thesis.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/16 15:53
 * @Description:
 */
@Service
public class DefaultTokenServiceImpl implements TokenService {


    @Autowired
    private SecurityProperties security;

    LoadingCache<String, UserDetails> tokenCache = CacheBuilder.newBuilder()
            .maximumSize(10000)
            .expireAfterAccess(30, TimeUnit.MINUTES)
            .build(new CacheLoader<String, UserDetails>() {
                @Override
                public UserDetails load(String key) throws Exception {
                    return security.getUser();
                }
            });

    @Override
    public UserDetails getUserInfoFromToken(String token) throws ExecutionException {
        return tokenCache.get(token);
    }

    @Override
    public boolean isValidToken(String token) throws ExecutionException {
        return tokenCache.get(token) != security.getUser();
    }

    @Override
    public void refreshToken(String token, byte status) throws ExecutionException {
        UserDetails user = tokenCache.get(token);
        boolean enabled = (status & 8) != 8;
        boolean accountNonExpired = (status & 4) != 4;
        boolean credentialsNonExpired = (status & 2) != 2;
        boolean accountNonLocked = (status & 1) != 1;
        tokenCache.put(token, new User(user.getUsername(), user.getPassword(), enabled, accountNonExpired
                , credentialsNonExpired, accountNonLocked, user.getAuthorities()));
    }

    @Override
    public void saveToken(String token, UserDetails userDetails) {
        tokenCache.put(token, userDetails);
    }
}
