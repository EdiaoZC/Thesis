package com.thesis.common.constants;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/8 16:25
 * @Description:
 */
@Slf4j
public class SystemProperties {

    public static String failureUrl;

    public static String loginUrl;


    @Value("${failureUrl}")
    public String failUrl;

    @Value("${loginUrl}")
    public String logUrl;

    @PostConstruct
    public void init() {
        failureUrl = failUrl;
        loginUrl = logUrl;
    }

}
