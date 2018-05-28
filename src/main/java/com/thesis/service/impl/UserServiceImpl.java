package com.thesis.service.impl;

import com.thesis.common.constants.Error;
import com.thesis.common.exception.RegisterException;
import com.thesis.common.model.*;
import com.thesis.common.model.dto.UserRoleDto;
import com.thesis.common.model.form.UserForm;
import com.thesis.common.model.vo.UserVo;
import com.thesis.common.util.Md5Util;
import com.thesis.dao.mapper.UserMapper;
import com.thesis.service.RoleService;
import com.thesis.service.TokenService;
import com.thesis.service.UserRoleService;
import com.thesis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;

    @Override
    public User loadUserByName(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<String> register(UserForm userForm) throws RegisterException {
        String salt = Md5Util.gensalt(6);
        User user = new User();
        if (!userForm.getPassword().equals(userForm.getConfirmPassword())) {
            throw new RegisterException(Error.PASSWORD_ERROR);
        }
        BeanUtils.copyProperties(userForm, user);
        user.setSalt(salt);
        user.setPassword(Md5Util.getMD5AndSalt(userForm.getPassword(), salt));
        UserRoleDto userRoleDto = new UserRoleDto();
        userMapper.insertSelective(user);
        userRoleDto.setUserId(user.getId());
        userRoleDto.setRoleIds(userForm.getRoles());
        boolean result = userRoleService.updateUserRole(userRoleDto);
        if (!result) {
            return Response.<String>builder().code(400).msg("fail")
                    .data("患者角色与其他角色不能同时存在").build();
        }
        if (userMapper.insertSelective(user) != 1) {
            return Response.<String>builder().code(400)
                    .msg("fail").data("失败").build();
        }
        return Response.<String>builder().code(200)
                .msg("success").data("成功").build();
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
    @Transactional(rollbackFor = Exception.class)
    public boolean delUserById(Long id) {
        userRoleService.deleteByUserId(id);
        return userMapper.deleteByPrimaryKey(id) == 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<String> updateUserById(UserForm userForm, String token) {
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
            tokenService.refreshToken(user);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        UserRoleDto userRoleDto = new UserRoleDto();
        userRoleDto.setUserId(user.getId());
        userRoleDto.setRoleIds(userForm.getRoles());
        final boolean result = userRoleService.updateUserRole(userRoleDto);
        if (!result) {
            return Response.<String>builder().code(400).msg("fail")
                    .data("患者角色与其他角色不能同时存在").build();
        }
        if (userMapper.updateByPrimaryKeySelective(user) != 1) {
            return Response.<String>builder().code(400)
                    .msg("fail").data("失败").build();
        }
        return Response.<String>builder().code(200)
                .msg("success").data("成功").build();
    }

    @Override
    public UserVo getInfoById(Long id) {
        UserVo userVo = new UserVo();
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            return null;
        }
        BeanUtils.copyProperties(user, userVo);
        final Set<Role> roles = roleService.getRoleByUsername(user.getUsername());
        userVo.setRoles(roles);
        return userVo;
    }


}
