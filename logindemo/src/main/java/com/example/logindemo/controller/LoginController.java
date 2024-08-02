package com.example.logindemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.logindemo.model.User;
import com.example.logindemo.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    public String registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles("USER");
        user.setEnabled(1);
        
        userRepository.save(user);
        return "redirect:login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().invalidate();
        return "redirect:login";
    }

    @GetMapping("/access-denied")
    public String accessDenid() {
        return "accessDenied";
    }

    @GetMapping("/admin")
    public ResponseEntity<String> adminHome() {
        return ResponseEntity.ok("welcome admin");
    }
    
    
} 
