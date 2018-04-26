package com.thesis.common.webSocket;

import com.thesis.service.WebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/24 14:51
 * @Description:
 */
@Component
@Slf4j
public class WebSocketHandler extends TextWebSocketHandler {

    @Autowired
    private WebSocketService webSocketService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.debug("成功建立 WebSocket 连接");
        webSocketService.addSession(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.debug("关闭 WebSocket 连接");
        webSocketService.removeSession(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.debug("服务器收到消息" + new String(message.asBytes(), "utf-8"));
        session.sendMessage(new TextMessage(new String(message.asBytes(), "utf-8")));
    }
}
