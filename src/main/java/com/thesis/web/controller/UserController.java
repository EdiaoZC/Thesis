package com.thesis.web.controller;

import com.thesis.common.constants.Error;
import com.thesis.common.constants.SecurityProperties;
import com.thesis.common.model.Response;
import com.thesis.common.model.User;
import com.thesis.common.model.form.UserForm;
import com.thesis.common.model.vo.UserVo;
import com.thesis.common.util.RequestUtil;
import com.thesis.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;


/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/8 14:56
 * @Description:
 */
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private SecurityProperties security;
    @Autowired
    private HttpServletRequest request;


    @ApiOperation("注册用户")
    @ApiResponses({
            @ApiResponse(code = 201, message = "注册成功"),
            @ApiResponse(code = 401, message = "没有权限")}
    )
    @RequestMapping(method = RequestMethod.POST
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response<String> register(@Valid UserForm user, Errors errors) {
        log.info("userForm对象是:{}", user);
        if (errors.hasErrors()) {

        }
        return userService.register(user);
    }

    @ApiOperation("获取用户列表")
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<UserVo>> userList() {
        List<UserVo> users = userService.userList();
        return ResponseEntity.ok(users);
    }

    @ApiOperation("获取用户详细信息")
    @GetMapping(value = "/{id:\\d+}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity userInfo(@PathVariable("id") Long id) {
        UserVo user = userService.getInfoById(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Error.INFO_NOT_FOUND);
        }
        return ResponseEntity.ok(user);
    }


    @ApiOperation("删除用户")
    @DeleteMapping(value = "/{id:\\d+}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> userDel(@PathVariable("id") Long id) {
        if (userService.delUserById(id)) {
            return ResponseEntity.ok("success");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail");
    }

    @ApiOperation("更新用户")
    @PutMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response<String> userUpdateInfo(@Valid UserForm user, Errors errors) {
        String token = RequestUtil.getValue(request, security.getToken());
        log.info("token是多少:{}", token);
        if (errors.hasErrors()) {

        }
        return userService.updateUserById(user, token);
    }

}