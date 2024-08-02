package com.example.jdbcexample;

import lombok.Data;

@Data
public class Person {
    Integer id;
    String name;
    String createdAt;

    public Person(Integer id, String name, String createdAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
    }
}
