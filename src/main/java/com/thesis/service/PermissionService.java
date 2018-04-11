package com.thesis.service;

import java.util.Set;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/11 14:38
 * @Description:
 */
public interface PermissionService {

    Set<String> getUrlsByUserName(String username);

}
