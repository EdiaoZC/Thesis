package com.thesis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "com.thesis",
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION
                , value = {Controller.class, ControllerAdvice.class, EnableWebMvc.class})})
@EnableTransactionManagement
@ImportResource("classpath:/spring/applicationContext-dao.xml")
@PropertySource("classpath:properties/Security.properties")
public class RootConfig {
}
