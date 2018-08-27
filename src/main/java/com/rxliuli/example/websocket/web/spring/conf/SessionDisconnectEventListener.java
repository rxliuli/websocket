package com.rxliuli.example.websocket.web.spring.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/**
 * Socket 会话断开监听
 *
 * @author rxliuli
 */
@Component
public class SessionDisconnectEventListener implements ApplicationListener<SessionDisconnectEvent> {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private SocketSessionRegistry webAgentSessionRegistry;

    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        //login get from browser
        String user = sha.getNativeHeader("simpSessionId").get(0);
        String sessionId = sha.getSessionId();
        log.info("Socket 连接断开，用户：{}，会话：{}", user, sessionId);
        webAgentSessionRegistry.unregisterSessionId(user, sessionId);
    }
}
