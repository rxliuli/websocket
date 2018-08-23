package com.rxliuli.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rxliuli.example.websocket.Person;
import org.junit.Test;

/**
 * @author rxliuli
 */
public class JacksonTest {
    private final ObjectMapper om = new ObjectMapper();

    @Test
    public void testWrite() throws JsonProcessingException {
        final String rx = om.writeValueAsString(new Person(1L, "rx", false));
        System.out.println(rx);
    }
}
