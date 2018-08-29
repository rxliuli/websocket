package com.rxliuli.example.websocket.web.spring.socket;

import com.rxliuli.example.websocket.web.entity.Person;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * 接受和发送复杂类型的消息
 *
 * @author rxliuli
 */
@Controller
public class ComplexMessageSocket {
    /**
     * 接收/返回复杂类型 Person 的对象
     *
     * @param person Person 类对象
     * @return Person 类对象
     */
    @MessageMapping("/complexMessage")
    @SendTo("/topic/complexMessage/allClient")
    public Person complexMessage(Person person) {
        return new Person().setName("Mr. " + person.getName());
    }
}
