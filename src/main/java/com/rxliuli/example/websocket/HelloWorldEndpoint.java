package com.rxliuli.example.websocket;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

/**
 * Java 使用 WebSocket 进行双向通信
 *
 * @author rxliuli
 */
@ServerEndpoint("/hello")
public class HelloWorldEndpoint {
    @OnMessage
    public String hello(String message) {
        System.out.println("message: " + message);
        return message;
    }

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("WebSocket opened: " + session.getId());
    }

    @OnClose
    public void onClose(CloseReason closeReason) {
        System.out.println("WebSocket closed: " + closeReason.getReasonPhrase());
    }
}
