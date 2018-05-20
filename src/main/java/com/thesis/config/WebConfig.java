package com.thesis.config;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.thesis.common.model.DeviceStatus;
import com.thesis.common.webSocket.WebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/8 11:42
 * @Description:
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.thesis.web")
@Import(SwaggerConfig.class)
@EnableWebSocket
@ImportResource("classpath:spring/applicationContext-mvc.xml")
public class WebConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {


    @Autowired
    private WebSocketHandler socketHandler;

    @Autowired
    private AsyncTaskExecutor taskExecutor;

    private Long timeout = 100000L;


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        fastJsonConfig.setSerializeConfig(serializeConfig());
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        converters.add(fastJsonHttpMessageConverter);
    }

    private SerializeConfig serializeConfig() {
        SerializeConfig serializeConfig = new SerializeConfig();
        serializeConfig.configEnumAsJavaBean(DeviceStatus.class);
        return serializeConfig;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(socketHandler, "/chat");
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setDefaultTimeout(timeout);
        configurer.setTaskExecutor(taskExecutor);
    }
}
