package com.thesis.service;

import org.springframework.web.socket.WebSocketSession;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/24 19:59
 * @Description:
 */
public interface WebSocketService {


    /**
     * 向所有已连接服务端的服务器发送消息
     *
     * @param url 将要发送的地址
     */
    void sendMessage(String url);

    /**
     * 记录已经连接的会话
     *
     * @param session 会话
     */
    void addSession(WebSocketSession session);

    /**
     * 移处已经断开连接的会话
     *
     * @param session 会话
     */
    void removeSession(WebSocketSession session);
}
