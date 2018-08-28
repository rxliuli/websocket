package com.rxliuli.example.websocket.web.spring.socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

/**
 * 双向点对点推送控制器
 *
 * @author rxliuli
 */
@Controller
public class BilateralPushSocket {
    @Autowired
    private SimpMessagingTemplate template;

    /**
     * 点对点推送（双向通信）
     *
     * @param text      消息
     * @param sessionId 会话 id
     * @return 推送到当前会话的消息
     */
    @MessageMapping("/speak")
    @SendToUser("/push/bilateral/thisClient")
    public String speak(@Payload String text, @Header("simpSessionId") String sessionId) throws InterruptedException {
        //模拟处理其他事情
        Thread.sleep(1000L);
        return "[ " + sessionId + "] send: [" + text + "]";
    }
}
