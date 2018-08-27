package com.rxliuli.example.websocket.web.spring.socket;

import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

/**
 * 单向点对点推送服务端
 *
 * @author rxliuli
 */
@Controller
public class UnidirectionalPushSocket {
    /**
     * 从服务端推送消息到所有客户端
     * 这是单向推送到客户端的，不接受从客户端的输入
     */
    @SendToUser("/push/unidirectional/thisClient")
    public Object broadcasting() {
        return null;
    }
}
