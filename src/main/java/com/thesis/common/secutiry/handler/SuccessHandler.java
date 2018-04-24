package com.thesis.common.secutiry.handler;

import com.alibaba.fastjson.JSON;
import com.thesis.common.constants.Agent;
import com.thesis.common.constants.SecurityProperties;
import com.thesis.common.model.Vo.UserVo;
import com.thesis.common.util.CookieUtil;
import com.thesis.service.TokenService;
import jdk.nashorn.internal.parser.Token;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/11 16:08
 * @Description:
 */
@Component
@Slf4j
public class SuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SecurityProperties security;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response
            , Authentication authentication) throws IOException, ServletException {
        Object details = authentication.getPrincipal();
        PrintWriter writer = response.getWriter();
        String agent = request.getHeader(Agent.AGENT);
        if (details instanceof UserDetails) {
            UserDetails user = (UserDetails) details;
            String token = UUID.randomUUID().toString();
            tokenService.saveToken(token, user);
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            if (Agent.WEB.equals(agent)) {
                log.debug("将token写入cookie");
                CookieUtil.addCookie(request, response, security.getToken(), token, true, 30, TimeUnit.MINUTES);
            } else {
                UserVo userVo = new UserVo();
                BeanUtils.copyProperties(user, userVo);
                userVo.setToken(token);
                writer.write(JSON.toJSONString(userVo));
            }
        }
    }
}
