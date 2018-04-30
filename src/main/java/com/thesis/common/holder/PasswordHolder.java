package com.thesis.common.holder;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/28 15:27
 * @Description:
 */
public class PasswordHolder {

    private static ThreadLocal<String> passwordHolder = new ThreadLocal<>();


    public static void setPassword(String password) {
        passwordHolder.set(password);
    }

    public static void remove() {
        passwordHolder.remove();
    }

    public static String get() {
        return passwordHolder.get();
    }
}
