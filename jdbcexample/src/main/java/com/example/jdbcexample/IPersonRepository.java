package com.example.jdbcexample;

public interface IPersonRepository {
    Iterable<Person> findAll();
    Person findOne(Integer id);
    Person save(Person person);
}
