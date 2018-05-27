package com.thesis.service.impl;

import com.thesis.common.exception.AccountException;
import com.thesis.common.holder.RolesHolder;
import com.thesis.common.holder.TokenHolder;
import com.thesis.service.PermissionService;
import com.thesis.service.RoleService;
import com.thesis.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/11 13:58
 * @Description:
 */
@Slf4j
@Service
public class RbacService {

    private PathMatcher pathMatcher = new AntPathMatcher();

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private TokenService tokenService;

    private String[] prefixs = {"", "/views"};

    public boolean hasPermission(HttpServletRequest request, Authentication auth) {
        Object principal = auth.getPrincipal();
        UserDetails user = null;
        if (principal instanceof UserDetails) {
            user = (UserDetails) principal;
        }
        try {
            String token = TokenHolder.get();
            if (token != null) {
                user = tokenService.getUserInfoFromToken(token);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if (user != null) {
            Set<String> roles = roleService.getRoleByUsername(user.getUsername());
            RolesHolder.setRoles(roles);
            log.info("user对象是:{}", user);
            if (!user.isEnabled()) {
                throw new AccountException("账号被禁用");
            }
            if (!user.isAccountNonLocked()) {
                throw new AccountException("账号被锁定");
            }
            String username = user.getUsername();
            if (username != null) {
                Set<String> urls = getUrlsByUserName(username);
                if (urls == null) {
                    throw new AuthorizationServiceException("你没有相应权限，请联系系统管理员！");
                }
                String path = request.getRequestURI().replace(request.getServletContext().getContextPath(), "");
                for (String url : urls) {
                    for (String prefix : prefixs) {
                        if (pathMatcher.match(url, prefix + path)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * 根据用户获取用户可以访问的url
     *
     * @param username 用户名
     * @return 用户可以访问的用户权限
     */
    Set<String> getUrlsByUserName(String username) {
        return permissionService.getUrlsByUserName(username);
    }


}
