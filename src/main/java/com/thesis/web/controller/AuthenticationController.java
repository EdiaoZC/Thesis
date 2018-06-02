package com.thesis.web.controller;

import com.alibaba.fastjson.JSON;
import com.google.code.kaptcha.Constants;
import com.thesis.common.constants.Agent;
import com.thesis.common.holder.PasswordHolder;
import com.thesis.common.model.Response;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

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
        String agent = request.getHeader(Agent.AGENT);
        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            log.info("跳转的url是:{}", targetUrl);
            if (StringUtils.endsWithIgnoreCase(targetUrl, ".html") ||
                    (StringUtils.isNoneBlank(agent) && agent.startsWith(Agent.WEB))) {
                log.info("失败url是：{}", failureUrl);
                redirectStrategy.sendRedirect(request, response, failureUrl);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("请登录后再进行操作");
    }

    @ApiOperation("用户登陆")
    @PostMapping(value = "/user/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void login(String username, String password) {

    }

    @GetMapping(value = "/user/logout", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void logout(HttpServletRequest request, HttpServletResponse response) {

    }

}
