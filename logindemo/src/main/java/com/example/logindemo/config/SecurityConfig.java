package com.example.logindemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

import com.example.logindemo.service.CustomUserDetailsService;

@Configuration
public class SecurityConfig  { // (1)

    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/register", "/signup").permitAll()
				.requestMatchers("/admin/**", "/admin").hasRole("ADMIN")
				.anyRequest().authenticated()
			).formLogin(form -> form
			.loginPage("/login")
			.defaultSuccessUrl("/")
			.permitAll())
			.exceptionHandling((customizer) -> customizer.accessDeniedHandler(accessDeniedHandler()))
			.logout(
				(logout) -> logout
				.logoutUrl("/logout")
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.logoutSuccessUrl("/login?logout")
				.permitAll()
			);
		return http.build();
	}

	@Bean
    public AccessDeniedHandler accessDeniedHandler() {
        AccessDeniedHandlerImpl accessDeniedHandler = new AccessDeniedHandlerImpl();
        accessDeniedHandler.setErrorPage("/access-denied");
        return accessDeniedHandler;

	}
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }	
}

