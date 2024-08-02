package com.example.logindemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.logindemo.model.User;
import com.example.logindemo.repository.UserRepository;

@Component
public class StartupDataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        String username = "shyam";
        String password = "test";

        String roles = "ROLE_ADMIN";
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(roles);
        user.setEnabled(1);

        userRepository.save(user);


        System.out.println("User created bro");
    }
    
}
