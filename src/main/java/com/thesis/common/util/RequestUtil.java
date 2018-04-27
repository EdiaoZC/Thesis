package com.thesis.common.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/27 21:14
 * @Description:
 */
public class RequestUtil {

    public static String getValue(HttpServletRequest request, String key) {
        String value = CookieUtil.getCookie(request, key);
        if (value != null) {
            value = request.getParameter(key);
        }
        return value;
    }
}
