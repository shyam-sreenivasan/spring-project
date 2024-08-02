package com.example.jdbcexample;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PersonRepository implements IPersonRepository {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public Iterable<Person> findAll() {
        return jdbc.query("SELECT id, name, createdAt FROM Person", this::mapRowToPerson);
    }

    @Override
    public Person findOne(Integer id) {
        return jdbc.queryForObject("select id, name, createdAt from Person where id=?", this::mapRowToPerson, id.toString());
    }

    @Override
    public Person save(Person person) {
        jdbc.update(
            "insert into person (name, createdAt) values (?, ?)",
            person.getName(),
            person.getCreatedAt());
        return person;
    }

    private Person mapRowToPerson(ResultSet rs, int rowNum) throws SQLException {
        return new Person(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("createdAt")
        );
       
    }
    
}
