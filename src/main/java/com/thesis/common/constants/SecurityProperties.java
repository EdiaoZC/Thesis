package com.thesis.common.constants;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/16 17:03
 * @Description:
 */
@Component
@Getter
public class SecurityProperties {

    @Value("${token.head}")
    private String token;

    private UserDetails user = new User("null", "null", AuthorityUtils.NO_AUTHORITIES);

    @Value("${loginUrl}")
    private String loginUrl;

}
