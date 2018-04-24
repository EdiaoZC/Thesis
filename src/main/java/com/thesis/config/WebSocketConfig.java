package com.thesis.config;

import com.thesis.common.websocket.ChatHandler;
import com.thesis.common.websocket.WebSocketHandShakeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/11 16:30
 * @Description:
 */

@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private ChatHandler chatHandler;

    @Autowired
    private WebSocketHandShakeInterceptor interceptor;


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatHandler, "/chat").addInterceptors(interceptor);
    }


}
