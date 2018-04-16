package com.thesis.common.util;

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
public class CookieUtil {


    public static void addCookie(HttpServletResponse response, String name, String value) {
        addCookie(response, name, value, true, 30, TimeUnit.MINUTES);
    }

    public static void addCookie(HttpServletResponse response, String name, String value, boolean httpOnly
            , int duration, TimeUnit timeUnit) {
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(httpOnly);
        cookie.setMaxAge((int) timeUnit.toMillis(duration));
        response.addCookie(cookie);
    }


    public static String getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
