package com.rxliuli.example.websocket.web.spring;

import com.rxliuli.example.websocket.web.hello.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 使用 Scheduled 不停的推送信息
 *
 * @author rxliuli
 */
@Component
public class GameRefreshJob {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Scheduled(fixedDelay = 100 * 1000)
    public void pull() {
        simpMessagingTemplate.convertAndSend("/topic/broadcasting", new Person(1L, "rx", false));
    }
}
