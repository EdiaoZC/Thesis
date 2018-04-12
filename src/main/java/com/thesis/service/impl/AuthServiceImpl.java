package com.thesis.service.impl;

import com.thesis.common.exception.UsernameRepeatException;
import com.thesis.common.model.User;
import com.thesis.common.util.JwtTokenUtil;
import com.thesis.dao.mapper.UserMapper;
import com.thesis.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtTokenUtil jwtTokenUtil;
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.tokenHead}")
    private String tokenHead;


    @Override
    public User register(User user) {
        final String username = user.getUsername();
        if (userMapper.selectByUsername(username) != null) {
            throw new UsernameRepeatException("用户名重复");
        }
        final String rawPassword = user.getPassword();
        user.setPassword(passwordEncoder.encode(rawPassword));
        return userMapper.insertSelective(user) >= 1 ? user : null;
    }

    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }
}