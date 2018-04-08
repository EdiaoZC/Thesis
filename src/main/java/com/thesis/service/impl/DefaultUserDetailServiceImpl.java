package com.thesis.service.impl;

import com.thesis.common.holder.SaltHolder;
import com.thesis.common.secutiry.Md5PassEncoder;
import com.thesis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/8 15:14
 * @Description:
 */
@Service
@Slf4j
public class DefaultUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Md5PassEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.thesis.common.model.User user = userService.loadUserByName(username);
        SaltHolder.setSalt(user.getSalt());
        return new User(user.getUsername(), user.getPassword(), AuthorityUtils.NO_AUTHORITIES);
    }
}
