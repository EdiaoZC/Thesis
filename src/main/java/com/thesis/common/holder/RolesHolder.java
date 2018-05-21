package com.thesis.common.holder;

import java.util.List;
import java.util.Set;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/5/21 23:09
 * @Description:
 */
public class RolesHolder {

    private final static ThreadLocal<Set<String>> rolesHolder = new ThreadLocal<>();


    public static Set<String> getRoles() {
        return rolesHolder.get();
    }

    public static void setRoles(Set<String> roles) {
        rolesHolder.set(roles);
    }

    public static void remove() {
        rolesHolder.remove();
    }

}
