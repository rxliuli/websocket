package com.rxliuli.example.websocket.web.spring.conf;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/**
 * Socket 会话断开监听
 *
 * @author rxliuli
 */
@Component
public class SessionDisconnectEventListener extends BaseSessionEventListener<SessionDisconnectEvent> {
    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {
        using(event, (user, sessionId) -> webAgentSessionRegistry.getAllSessionIds().entrySet().stream()
                .filter(sse -> sse.getValue().contains(sessionId))
                .findFirst()
                .ifPresent(sse -> {
                    webAgentSessionRegistry.unregisterSessionId(sse.getKey(), sessionId);
                    log.info("Socket 连接断开，用户：{}，会话：{}", sse.getKey(), sessionId);
                }));
    }
}
