package com.thesis.service.impl;

import com.thesis.SpringTest;
import com.thesis.service.TokenService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/16 16:19
 * @Description:
 */
public class DefaultTokenServiceImplTest extends SpringTest {

    @Autowired
    private TokenService tokenService;

    @Test
    public void getUserInfoFromToken() throws ExecutionException {
        String token = UUID.randomUUID().toString();
        UserDetails user = new User("admin", "As110695", AuthorityUtils.NO_AUTHORITIES);
        Assert.assertEquals(tokenService.getUserInfoFromToken(token), DefaultTokenServiceImpl.user);
        tokenService.saveToken(token, user);
        Assert.assertEquals(tokenService.getUserInfoFromToken(token), DefaultTokenServiceImpl.user);
    }

    @Test
    public void isValidToken() {
    }

    @Test
    public void refreshToken() {
    }
}