package com.thesis.service.impl;

import com.thesis.common.holder.SaltHolder;
import com.thesis.common.model.User;
import com.thesis.dao.mapper.UserMapper;
import com.thesis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/8 14:24
 * @Description:
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User loadUserByName(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public int insertUser(User user) {
        user.setSalt(SaltHolder.getSalt());
        SaltHolder.remove();
        return userMapper.insertSelective(user);
    }


}
