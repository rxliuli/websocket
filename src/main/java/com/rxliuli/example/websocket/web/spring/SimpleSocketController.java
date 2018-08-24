package com.rxliuli.example.websocket.web.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

/**
 * 简单的 Socket 控制器
 *
 * @author rxliuli
 */
@Controller
public class SimpleSocketController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 广播推送
     * 注解 @Payload 是为了绑定消息到参数 text 上
     *
     * @param text      简单的文本信息
     * @param sessionId 当前请求 socket 会话 id
     * @return 会话 id 和消息内容
     */
    @MessageMapping(value = "/talk")
    @SendTo("/topic/allClient")
    public String talk(@Payload String text, @Header("simpSessionId") String sessionId) {
        //模拟处理其他事情
        return "[ " + sessionId + "] 说: [" + text + "]";
    }

    /**
     * 点对点推送（双向通信）
     *
     * @param text      消息
     * @param sessionId 会话 id
     * @return 推送到当前会话的消息
     */
    @MessageMapping("/speak")
    @SendToUser("/thisClient")
    public String speak(@Payload String text, @Header("simpSessionId") String sessionId) {
        return "[ " + sessionId + "] 说: [" + text + "]";
    }
}
