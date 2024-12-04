package com.javaweb.buildingproject.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http
            ,CustomAuthenticationEntryPoint customAuthenticationEntryPoint) throws Exception {
        http.csrf(c->c.disable())
            .cors(c->c.disable());
        http.authorizeHttpRequests(author -> author
                        .requestMatchers("/api/v1/auth/login","/api/v1/auth/refresh").permitAll()
                        .anyRequest().authenticated());
        http.oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(Customizer.withDefaults())
                        .authenticationEntryPoint(customAuthenticationEntryPoint));
        http.formLogin(f -> f.disable());
        http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

}
