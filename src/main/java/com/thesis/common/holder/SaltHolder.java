package com.thesis.common.holder;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/8 16:35
 * @Description:
 */
public class SaltHolder {

    private static ThreadLocal<String> saltHolder = new ThreadLocal<>();

    public static String getSalt() {
        return saltHolder.get();
    }

    public static void setSalt(String salt) {
        saltHolder.set(salt);
    }

    public static void remove() {
        saltHolder.remove();
    }

}
