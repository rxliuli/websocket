package com.rxliuli.example.websocket.web.spring;

import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * 广播信息到所有连接的客户端
 *
 * @author rxliuli
 */
@Controller
public class BroadcastingToClientController {
    /**
     * 从服务端推送消息到客户端
     * 这是单向推送到客户端的，不接受从客户端的输入
     */
    @SendTo("/topic/broadcasting")
    public Object broadcasting() {
        return null;
    }
}
