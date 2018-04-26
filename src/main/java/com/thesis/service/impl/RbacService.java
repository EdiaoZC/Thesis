package com.thesis.service.impl;

import com.thesis.common.holder.TokenHolder;
import com.thesis.service.PermissionService;
import com.thesis.service.RoleService;
import com.thesis.service.TokenService;
import com.thesis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
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
    private TokenService tokenService;

    public boolean hasPermission(HttpServletRequest request, Authentication auth) {
        Object principal = auth.getPrincipal();
        log.debug(principal.getClass().getName());
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        try {
            String token = TokenHolder.get();
            if (token != null) {
                username = tokenService.getUserInfoFromToken(token).getUsername();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if (username != null) {
            Set<String> urls = getUrlsByUserName(username);
            log.debug("url对象是:{}", urls);
            if (urls == null) {
                throw new AuthorizationServiceException("你没有相应权限，请联系系统管理员！");
            }
            String path = request.getRequestURI().replace(request.getServletContext().getContextPath(), "");
            for (String url : urls) {
                log.debug(url + "========" + path);
                if (pathMatcher.match(url, path)) {
                    log.debug("成功通过校验");
                    return true;
                }
            }
        }
        log.debug("权限未通过校验");
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
