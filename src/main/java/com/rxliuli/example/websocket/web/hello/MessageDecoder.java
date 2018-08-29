package com.rxliuli.example.websocket.web.hello;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rxliuli.example.websocket.web.entity.Person;

import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.IOException;

/**
 * @author rxliuli
 */
public class MessageDecoder implements Decoder.Text<Person> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }

    @Override
    public Person decode(String s) {
        try {
            return objectMapper.readValue(s, Person.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean willDecode(String s) {
        return s != null;
    }
}
