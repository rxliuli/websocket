package com.rxliuli.example.websocket.web.entity;

import java.io.Serializable;

/**
 * @author rxliuli
 */
public class Person implements Serializable {
    private Long id;
    private String name;
    private boolean sex;

    public Person() {
    }

    public Person(Long id, String name, boolean sex) {

        this.id = id;
        this.name = name;
        this.sex = sex;
    }

    public Long getId() {

        return id;
    }

    public Person setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isSex() {
        return sex;
    }

    public Person setSex(boolean sex) {
        this.sex = sex;
        return this;
    }
}