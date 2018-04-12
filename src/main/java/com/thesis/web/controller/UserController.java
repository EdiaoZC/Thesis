package com.thesis.web.controller;

import com.thesis.common.holder.SaltHolder;
import com.thesis.common.model.User;
import com.thesis.common.secutiry.Md5PassEncoder;
import com.thesis.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


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



    @ApiOperation("注册用户")
    @ApiResponses({
            @ApiResponse(code = 201, message = "注册成功"),
            @ApiResponse(code = 401, message = "没有权限")}
    )
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> register(User user) {
        String encodePassword = md5PassEncoder.encode(user.getPassword());
        user.setSalt(SaltHolder.getSalt());
        user.setPassword(encodePassword);
        log.info("{}", user);
        userService.insertUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("success");
    }


}