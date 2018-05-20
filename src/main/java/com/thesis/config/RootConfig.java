package com.thesis.config;

import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/8 11:42
 * @Description:
 */
@Configuration
@ComponentScan(basePackages = "com.thesis",
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION
                , value = {Controller.class, ControllerAdvice.class, EnableWebMvc.class})})
@EnableTransactionManagement
@ImportResource("classpath:/spring/applicationContext-dao.xml")
@PropertySource( {"classpath:properties/security.properties"})
public class RootConfig {
}
