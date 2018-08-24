package com.rxliuli.example.websocket.web.spring.socket;

import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * 单向广播控制器
 *
 * @author rxliuli
 */
@Controller
public class UnidirectionalBroadcastingSocket {
    /**
     * 从服务端推送消息到所有客户端
     * 这是单向推送到客户端的，不接受从客户端的输入
     */
    @SendTo("/topic/broadcasting/unidirectional/allClient")
    public Object broadcasting() {
        return null;
    }
}
