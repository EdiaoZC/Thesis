package com.thesis.config;

import com.thesis.common.security.filter.TokenFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.HttpPutFormContentFilter;


/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/8 11:42
 * @Description:
 */
@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
    @Autowired
    private AuthenticationFailureHandler failureHandler;
    @Autowired
    private AuthenticationSuccessHandler successHandler;
    @Autowired
    private TokenFilter tokenFilter;

    private static final String[] AUTH_WHITELIST = {
            // -- swagger ui
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/authentication/require",
            "/login.html",
            "/chat",
            "/css/**",
            "/images/**",
            "/js/**",
            "/layui/**",
            "/json/**",
            "/login.html",
            // other public endpoints of your API may be appended to this array
    };

    @Value("${loginUrl}")
    private String loginUrl;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl(loginUrl)
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .and().headers().frameOptions().sameOrigin() //允许同源网站加载frame
                .and().authorizeRequests()
                .antMatchers(HttpMethod.GET, AUTH_WHITELIST).permitAll()
                .anyRequest().access("@rbacService.hasPermission(request, authentication)")
                .and().csrf().disable().exceptionHandling().accessDeniedHandler(accessDeniedHandler)
                .and().addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
