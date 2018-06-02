package com.thesis.web.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.thesis.common.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/5/30 14:31
 * @Description: 生成验证码
 */
@Controller
@RequestMapping("/captcha")
@Slf4j
public class CaptchaController {

    @Autowired
    private Producer producer;

    @RequestMapping(method = RequestMethod.GET)
    public void getImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        String capText = producer.createText();
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        BufferedImage bi = producer.createImage(capText);
        try (ServletOutputStream out = response.getOutputStream()) {
            ImageIO.write(bi, "JPEG", out);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/verify", method = RequestMethod.GET)
    public Object verify(HttpServletRequest request, String code) throws IOException {
        String captcha = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (StringUtils.isNoneBlank(captcha)) {
            if (!captcha.equalsIgnoreCase(code)) {
                return Response.builder().code(400).msg("验证码错误").build();
            } else {
                return Response.builder().code(200).msg("验证通过").build();
            }
        }
        return Response.builder().code(400).msg("服务器内部错误").build();
    }
}