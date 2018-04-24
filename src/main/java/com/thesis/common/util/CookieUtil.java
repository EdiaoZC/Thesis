package com.thesis.common.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/16 17:34
 * @Description:
 */
@Slf4j
public class CookieUtil {



    public static void addCookie(HttpServletRequest request, HttpServletResponse response
            , String name, String value) {
        addCookie(request, response, name, value, true, 30, TimeUnit.MINUTES);
    }

    public static void addCookie(HttpServletRequest request, HttpServletResponse response
            , String name, String value, boolean httpOnly, int duration, TimeUnit timeUnit) {
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(httpOnly);
        String path = request.getServletContext().getContextPath();
        log.info(path);
        cookie.setPath(path);
        cookie.setMaxAge((int) timeUnit.toMillis(duration));
        response.addCookie(cookie);
    }


    public static String getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
