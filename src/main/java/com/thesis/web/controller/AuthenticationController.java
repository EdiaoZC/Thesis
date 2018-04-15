package com.thesis.web.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/10 15:08
 * @Description:
 */
@Slf4j
@Controller
public class AuthenticationController {


    @Value("${failureUrl}")
    private String failureUrl;

    @Value("${jwt.header}")
    private String tokenHeader;

    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();


    @ApiOperation("校验用户登陆界面功能")
    @GetMapping(value = "/authentication/require", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> require(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            log.info("跳转的url是:{}", targetUrl);
            if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
                log.info("失败url是：{}", failureUrl);
                redirectStrategy.sendRedirect(request, response, failureUrl);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("请登录后再进行操作");
    }

    @ApiOperation("用户登陆")
    @PostMapping(value = "/user/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> login(String username, String password) {
        return ResponseEntity.ok("hello");
    }

}
