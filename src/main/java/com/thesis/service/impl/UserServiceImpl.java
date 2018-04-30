package com.thesis.service.impl;

import com.thesis.common.constants.Error;
import com.thesis.common.exception.RegisterException;
import com.thesis.common.model.AccountStatus;
import com.thesis.common.model.User;
import com.thesis.common.model.form.UserForm;
import com.thesis.common.model.vo.UserVo;
import com.thesis.common.util.Md5Util;
import com.thesis.dao.mapper.UserMapper;
import com.thesis.service.TokenService;
import com.thesis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


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

    @Autowired
    private TokenService tokenService;

    @Override
    public User loadUserByName(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int register(UserForm userForm) throws RegisterException {
        String salt = Md5Util.gensalt(6);
        User user = new User();
        if (!userForm.getPassword().equals(userForm.getConfirmPassword())) {
            throw new RegisterException(Error.PASSWORD_ERROR);
        }
        BeanUtils.copyProperties(userForm, user);
        user.setSalt(salt);
        user.setPassword(Md5Util.getMD5AndSalt(userForm.getPassword(), salt));
        log.debug("user对象是:{}", user);
        return userMapper.insertSelective(user);
    }

    @Override
    public List<UserVo> userList() {
        List<User> users = userMapper.getAllInfo();
        List<UserVo> userVos = new ArrayList<>();
        for (User user : users) {
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(user, userVo);
            userVo.setSex(user.getSex().getSex());
            userVo.setStatus(getStatusInfo(user.getStatus()));
            userVos.add(userVo);
        }
        return userVos;
    }

    private List<String> getStatusInfo(int status) {
        List<String> results = new ArrayList<>();
        if ((status & AccountStatus.ENABLED) == AccountStatus.ENABLED) {
            results.add("禁用");
        }
        if ((status & AccountStatus.ACCOUNT_NON_LOCKED) == AccountStatus.ACCOUNT_NON_LOCKED) {
            results.add("锁定");
        }
        if (results.size() == 0) {
            results.add("正常");
        }
        return results;
    }

    @Override
    public boolean delUserById(Long id) {
        return userMapper.deleteByPrimaryKey(id) == 1;
    }

    @Override
    public boolean updateUserById(UserForm userForm, String token) {
        User user = new User();
        log.info("token是多少:{}", token);
        BeanUtils.copyProperties(userForm, user);
        byte status = 0;
        if (userForm.getStatus() != null) {
            for (Byte num : userForm.getStatus()) {
                status |= num;
            }
        }
        user.setStatus(status);
        try {
            tokenService.refreshToken(token, status);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return userMapper.updateByPrimaryKeySelective(user) == 1;
    }

    @Override
    public User getInfoById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }


}
