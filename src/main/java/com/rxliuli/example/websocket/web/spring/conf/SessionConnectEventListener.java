package com.rxliuli.example.websocket.web.spring.conf;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;

/**
 * Socket 连接建立监听
 * 用于 session 注册 以及 key 值获取
 *
 * @author rxliuli
 */
@Component
public class SessionConnectEventListener extends BaseSessionEventListener<SessionConnectEvent> {
    @Override
    public void onApplicationEvent(SessionConnectEvent event) {
        using(event, (user, sessionId) -> webAgentSessionRegistry.registerSessionId(user, sessionId));
    }
}