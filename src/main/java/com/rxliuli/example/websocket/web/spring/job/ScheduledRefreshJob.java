package com.rxliuli.example.websocket.web.spring.job;

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
public class ScheduledRefreshJob {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    /**
     * 不停地推送消息到客户端
     */
    @Scheduled(fixedDelay = 10 * 1000)
    public void scheduledBroadcasting() {
        simpMessagingTemplate.convertAndSend("/topic/broadcasting/unidirectional/allClient", new Person(1L, "rx", false));
    }

    @Scheduled(fixedDelay = 10 * 1000)
    public void scheduledPush() {
        simpMessagingTemplate.convertAndSendToUser("", "/push/unidirectional/thisClient", new Person(2L, "琉璃", false));
    }
}
