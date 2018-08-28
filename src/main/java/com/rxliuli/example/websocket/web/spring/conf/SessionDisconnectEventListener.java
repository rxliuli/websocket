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
        using(event, (user, sessionId) -> webAgentSessionRegistry.unregisterSessionId(user, sessionId));
    }
}
