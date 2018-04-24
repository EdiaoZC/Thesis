package com.thesis.common.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/24 14:51
 * @Description:
 */
@Component
@Slf4j
public class ChatHandler extends AbstractWebSocketHandler {

    private final static CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        session.sendMessage(new TextMessage("hello world"));
    }
}
