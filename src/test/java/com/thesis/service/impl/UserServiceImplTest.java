package com.thesis.service.impl;

import com.thesis.SpringTest;
import com.thesis.common.model.User;
import com.thesis.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/15 14:32
 * @Description:
 */
public class UserServiceImplTest extends SpringTest {

    @Autowired
    private UserService userService;

    @Test
    public void loadUserByName() {
        User admin = userService.loadUserByName("admin");
        System.out.println(admin);
    }


    @Test
    public void register() {
    }

    @Test
    public void userList() {
    }

    @Test
    public void delUserById() {
    }

    @Test
    public void updateUserById() {
    }

    @Test
    public void getInfoById() {
        Long id = 3L;
        userService.getInfoById(id);
    }
}