package com.thesis.common.security.filter;

import com.alibaba.fastjson.JSON;
import com.thesis.common.constants.Agent;
import com.thesis.common.constants.Error;
import com.thesis.common.constants.SecurityProperties;
import com.thesis.common.holder.TokenHolder;
import com.thesis.common.model.Response;
import com.thesis.common.util.CookieUtil;
import com.thesis.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/16 16:55
 * @Description:
 */
@Component
@Slf4j
public class TokenFilter extends OncePerRequestFilter {


    @Autowired
    private SecurityProperties security;
    @Autowired
    private TokenService tokenService;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response
            , FilterChain filterChain) throws ServletException, IOException {
        String token = null;
        token = CookieUtil.getCookie(request, security.getToken());
        if (token == null) {
            token = request.getParameter(security.getToken());
        }
        log.debug("token:{}", token);
        TokenHolder.set(token);
        if (token != null) {
            try {
                if (!tokenService.isValidToken(token)) {
                    log.debug("token验证未通过");
                    if (Agent.WEB.equals(request.getHeader(Agent.AGENT))) {
                        log.debug("清除cookie中的token");
                        CookieUtil.addCookie(request, response, security.getToken(), null, true, 0, TimeUnit.MINUTES);
                        redirectStrategy.sendRedirect(request, response, security.getLoginUrl());
                    } else {
                        PrintWriter writer = response.getWriter();
                        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                        writer.write(JSON.toJSONString(Response.builder().msg(Error.TOKEN_ERROR).build()));
                        writer.close();
                    }
                } else {
                    filterChain.doFilter(request, response);
                }
            } catch (ExecutionException e) {
                log.error("发生异常:{}", e);
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
