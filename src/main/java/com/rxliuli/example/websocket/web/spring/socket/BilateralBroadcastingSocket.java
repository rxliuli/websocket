package com.rxliuli.example.websocket.web.spring.socket;

import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * 双向广播控制器
 *
 * @author rxliuli
 */
@Controller
public class BilateralBroadcastingSocket {
    /**
     * 广播推送
     * 注解 @Payload 是为了绑定消息到参数 text 上
     *
     * @param text      简单的文本信息
     * @param sessionId 当前请求 socket 会话 id
     * @return 会话 id 和消息内容
     */
    @MessageMapping(value = "/talk")
    @SendTo("/topic/broadcasting/bilateral/allClient")
    public String talk(@Payload String text, @Header("simpSessionId") String sessionId) throws InterruptedException {
        //模拟处理其他事情
        Thread.sleep(1000L);
        return "[ " + sessionId + "] 说: [" + text + "]";
    }
}
