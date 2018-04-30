package com.thesis.common.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/27 21:14
 * @Description:
 */
@Slf4j
public class RequestUtil {

    public static String getValue(HttpServletRequest request, String key) {
        log.info("request对象是:{}", request);
        String value = CookieUtil.getCookie(request, key);
        if (value == null) {
            value = request.getParameter(key);
        }
        return value;
    }
}
