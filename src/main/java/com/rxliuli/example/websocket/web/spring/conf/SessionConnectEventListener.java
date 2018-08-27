package com.rxliuli.example.websocket.web.spring.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;

/**
 * Socket 连接建立监听
 * 用于 session 注册 以及 key 值获取
 *
 * @author rxliuli
 */
@Component
public class SessionConnectEventListener implements ApplicationListener<SessionConnectEvent> {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private SocketSessionRegistry webAgentSessionRegistry;

    @Override
    public void onApplicationEvent(SessionConnectEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        //login get from browser
        String user = sha.getNativeHeader("simpSessionId").get(0);
        String sessionId = sha.getSessionId();
        log.info("Socket 连接成功，用户：{}，会话：{}", user, sessionId);
        webAgentSessionRegistry.registerSessionId(user, sessionId);
    }
}