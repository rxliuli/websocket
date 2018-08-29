package com.rxliuli.example.websocket.web.hello;

import com.rxliuli.example.websocket.web.entity.Person;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * Java 使用 WebSocket 进行双向通信
 *
 * @author rxliuli
 */
@ServerEndpoint(value = "/hello",
        encoders = {
                MessageEncoder.class
        },
        decoders = {
                MessageDecoder.class
        }
)
public class HelloWorldEndpoint {
    @OnMessage
    public Person hello(Person person, Session session) {
        try {
            person.setName("Mr. " + person.getName());
            //这里发送对象到客户端时也会进行序列化之后再发送
            session.getBasicRemote().sendObject(person);
        } catch (IOException | EncodeException e) {
            e.printStackTrace();
        }
        return person;
    }

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("WebSocket opened: " + session.getId());
    }

    @OnClose
    public void onClose(CloseReason closeReason) {
        System.out.println("WebSocket closed: " + closeReason.getReasonPhrase());
    }
}
