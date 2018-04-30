package com.thesis.service.impl;

import com.thesis.common.holder.PasswordHolder;
import com.thesis.common.holder.SaltHolder;
import com.thesis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/8 15:14
 * @Description:
 */
@Slf4j
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.thesis.common.model.User user = userService.loadUserByName(username);
        log.debug("获取到的用户信息是" + user);
        if (user == null) {
            throw new BadCredentialsException("用户名或密码错误");
        }
        SaltHolder.setSalt(user.getSalt());
        Byte status = user.getStatus();
        boolean enabled = (status & 8) != 8;
        boolean accountNonExpired = (status & 4) != 4;
        boolean credentialsNonExpired = (status & 2) != 2;
        boolean accountNonLocked = (status & 1) != 1;
        PasswordHolder.setPassword(user.getPassword());
        return new User(user.getUsername(), user.getPassword(), enabled, accountNonExpired
                , credentialsNonExpired, accountNonLocked, AuthorityUtils.NO_AUTHORITIES);
    }
}
