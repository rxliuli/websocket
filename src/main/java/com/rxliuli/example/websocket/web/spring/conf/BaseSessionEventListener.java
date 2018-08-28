package com.rxliuli.example.websocket.web.spring.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.messaging.AbstractSubProtocolEvent;

import java.util.List;
import java.util.function.BiConsumer;

/**
 * 会话事件监听基类
 *
 * @author rxliuli
 */
public abstract class BaseSessionEventListener<Event extends AbstractSubProtocolEvent> implements ApplicationListener<Event> {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    protected SocketSessionRegistry webAgentSessionRegistry;

    /**
     * 计算出 user id 和 session id 并传入到自定义的函数中
     *
     * @param event      事件
     * @param biConsumer 自定义的操作
     */
    protected void using(Event event, BiConsumer<String, String> biConsumer) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        //login get from browser
        List<String> shaNativeHeader = sha.getNativeHeader("Authorization");
        String user;
        if (shaNativeHeader == null || shaNativeHeader.isEmpty()) {
            user = null;
        } else {
            user = shaNativeHeader.get(0);
        }
        //如果当前用户没有登录（没有认证信息），就添加到游客里面
        if (user == null || "".equals(user) || "undefined".equals(user) || "null".equals(user)) {
            user = SocketSessionRegistry.DIRECT_TOURIST;
        }
        String sessionId = sha.getSessionId();
        biConsumer.accept(user, sessionId);
    }
}
