package com.example.logindemo.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.logindemo.model.User;
import com.example.logindemo.repository.UserRepository;

public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        User user = userRepository.findByUsername(username).orElseThrow(
            () -> new UsernameNotFoundException("User " + username + "not found")
        );
        
        return getAsUserDetails(user);
    }
    
    public UserDetails getAsUserDetails(User user) {
        List<GrantedAuthority> grantedAuthorities = Arrays.stream(user.getRoles().split(","))
                .map(String::trim)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
        user.getUsername(),
        user.getPassword(),
        user.isEnabled(),
        true, // accountNonExpired
        true, // credentialsNonExpired
        true, // accountNonLocked
        grantedAuthorities
        );
    }
}
//$2a$12$Yvno7.WpOSq/2bhKURfZMeapOdMxKKADEZI2GAyGGTALgJprIc3xW - test