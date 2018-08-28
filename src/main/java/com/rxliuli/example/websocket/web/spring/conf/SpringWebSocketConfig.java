package com.rxliuli.example.websocket.web.spring.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * 配置 SpringBoot WebSocket 支持
 *
 * @author rxliuli
 */
@Configuration
@EnableWebSocketMessageBroker
public class SpringWebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
    /**
     * 注册一个 Socket 端点
     *
     * @param stompEndpointRegistry stomp 端点注册表
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        stompEndpointRegistry.addEndpoint("/endpoint")
                //设置允许所有源请求（跨域）
                .setAllowedOrigins("*")
                .withSockJS();
    }

    /**
     * 注册一些广播消息代理
     *
     * @param registry 消息代理注册对象
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //注册简单代理（里面是前缀）
        registry.enableSimpleBroker("/topic", "/user");
    }
}
