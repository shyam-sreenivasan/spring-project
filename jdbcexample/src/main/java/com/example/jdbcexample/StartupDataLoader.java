package com.example.jdbcexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupDataLoader implements CommandLineRunner{

    @Autowired
    PersonRepository personRepository;

    
    @Override
    public void run(String... args) throws Exception {
       Person p = new Person(2, "shyam", "2024-01-01 12:00:00");
       personRepository.save(p);
    }
    
}
