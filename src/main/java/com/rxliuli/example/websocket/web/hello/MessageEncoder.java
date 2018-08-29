package com.rxliuli.example.websocket.web.hello;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rxliuli.example.websocket.web.entity.Person;

import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * @author rxliuli
 */
public class MessageEncoder implements Encoder.Text<Person> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }

    @Override
    public String encode(Person person) {
        try {
            return objectMapper.writeValueAsString(person);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
