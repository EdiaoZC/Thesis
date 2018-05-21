package com.thesis.common.holder;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/5/21 23:19
 * @Description:
 */
public class UserHolder {

    private static final ThreadLocal<String> userHolder = new ThreadLocal<>();

    public static String getUser() {
        return userHolder.get();
    }

    public static void setUser(String username) {
        userHolder.set(username);
    }

    public static void remove() {
        userHolder.remove();
    }
}
