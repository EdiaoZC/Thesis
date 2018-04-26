package com.thesis.service.impl;

import com.thesis.service.WebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/24 20:02
 * @Description:
 */
@Service
@Slf4j
public class WebSocketServiceImpl implements WebSocketService {


    private CopyOnWriteArrayList<WebSocketSession> sessions;

    public WebSocketServiceImpl() {
        this.sessions = new CopyOnWriteArrayList<>();
    }

    @Override
    public void sendMessage(final String url) {
        log.info("要发送的url是:{}", url);
        sessions.forEach(session -> {
            try {
                session.sendMessage(new TextMessage(url));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void addSession(WebSocketSession session) {
        sessions.add(session);
    }

    @Override
    public void removeSession(WebSocketSession session) {
        sessions.remove(session);
    }
}
