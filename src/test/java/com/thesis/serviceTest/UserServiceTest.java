package com.thesis.serviceTest;

import com.thesis.SpringTest;
import com.thesis.common.model.User;
import com.thesis.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/8 17:28
 * @Description:
 */
public class UserServiceTest extends SpringTest {

    @Autowired
    private UserService userService;

    @Test
    public void UserServiceTest() {
        System.out.println(userService.loadUserByName("admin"));
    }
}
