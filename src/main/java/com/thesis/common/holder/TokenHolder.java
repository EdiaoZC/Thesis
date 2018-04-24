package com.thesis.common.holder;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/24 14:35
 * @Description:
 */
public class TokenHolder {

    private static ThreadLocal<String> tokenHolder = new ThreadLocal<>();

    public static void set(String token) {
        tokenHolder.set(token);
    }

    public static String get() {
        return tokenHolder.get();
    }

    public static void remove() {
        tokenHolder.remove();
    }
}
