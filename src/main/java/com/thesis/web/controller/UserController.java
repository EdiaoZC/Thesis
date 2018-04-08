package com.thesis.web.controller;

import com.thesis.common.holder.SaltHolder;
import com.thesis.common.model.User;
import com.thesis.common.secutiry.Md5PassEncoder;
import com.thesis.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/8 14:56
 * @Description:
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private Md5PassEncoder md5PassEncoder;


    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "用户登陆")
    public ResponseEntity<String> login(@RequestParam("username") String username
            , @RequestParam("password") String password) {
        return ResponseEntity.ok("success");
    }


    @ApiOperation("注册用户")
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> register(User user) {
        String encodePassword = md5PassEncoder.encode(user.getPassword());
        user.setSalt(SaltHolder.getSalt());
        user.setPassword(encodePassword);
        log.info("{}", user);
        userService.insertUser(user);
        return ResponseEntity.ok("success");
    }


}