package com.example.jdbcexample;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @GetMapping("")
    public ResponseEntity<List<Person>> getPersons() {
        List<Person> persons = (List<Person>) personRepository.findAll();
        return ResponseEntity.ok().body(persons);
    }
    
}
