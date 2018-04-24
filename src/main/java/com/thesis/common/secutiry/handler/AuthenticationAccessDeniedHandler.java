package com.thesis.common.secutiry.handler;

import com.alibaba.fastjson.JSON;
import com.thesis.common.constants.Error;
import com.thesis.common.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/11 16:16
 * @Description:
 */
@Component
@Slf4j
public class AuthenticationAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response
            , AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        log.error("发生异常{}", accessDeniedException.getMessage());
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(Response.builder().code(HttpServletResponse.SC_FORBIDDEN)
                .msg(Error.ACCESS_DENIED).build()));
    }
}
