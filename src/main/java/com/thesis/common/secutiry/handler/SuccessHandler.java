package com.thesis.common.secutiry.handler;

import com.alibaba.fastjson.JSON;
import com.thesis.common.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.print.attribute.standard.Media;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/11 16:08
 * @Description:
 */
@Component
@Slf4j
public class SuccessHandler implements AuthenticationSuccessHandler {


    @Autowired
    private JwtTokenUtil tokenUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response
            , Authentication authentication) throws IOException, ServletException {
        Object details = authentication.getPrincipal();
        log.debug("details的对象是：{}", details.getClass().getName());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        PrintWriter writer = response.getWriter();
        if (details instanceof UserDetails) {
            UserDetails userToken = (UserDetails) details;
            String token = tokenUtil.generateToken(userToken);
            log.debug("生成的token为:{}", token);
            writer.write(JSON.toJSONString(token));
        }
        writer.flush();
        writer.close();
    }
}
