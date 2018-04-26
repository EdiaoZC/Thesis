package com.thesis.common.security.handler;

import com.alibaba.fastjson.JSON;
import com.thesis.common.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/11 15:50
 * @Description:
 */
@Slf4j
@Component
public class FailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response
            , AuthenticationException exception) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        PrintWriter writer = response.getWriter();
        if (exception instanceof InternalAuthenticationServiceException) {
            writer.write(JSON.toJSONString(Response.builder().msg(exception.getMessage()).build()));
        }
    }
}
