package com.example.backend.security;
// src/main/java/com/auth0/example/security/SecurityConfig.java
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.springframework.web.cors.CorsConfiguration;
//
//import java.util.Arrays;
//
//@EnableWebSecurity
//public class SecurityConfig {
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .cors().configurationSource(request -> {
//                    var cors = new CorsConfiguration();
//                    cors.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
//                    cors.setAllowedMethods(Arrays.asList("GET", "POST"));
//                    cors.setAllowedHeaders(Arrays.asList("*"));
//                    return cors;
//                })
//                .and()
//                .authorizeRequests()
//                .requestMatchers(AntPathRequestMatcher.antMatcher("/api/public")).permitAll()
//                .requestMatchers(AntPathRequestMatcher.antMatcher("/api/private")).authenticated()
//                .requestMatchers(AntPathRequestMatcher.antMatcher("/api/private-scoped")).hasAuthority("read:messages")
//                .anyRequest().authenticated()
//                .and().cors()
//                .and().oauth2ResourceServer().jwt();
//        return http.build();
//
//    }
//}

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Value("${okta.oauth2.issuer}")
    private String issuer;
    @Value("${okta.oauth2.client-id}")
    private String clientId;
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/api/v1/private")).authenticated()
                        .anyRequest().authenticated()
                )
                .cors(withDefaults())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(withDefaults())
                )
                .logout(logout -> logout
                        .addLogoutHandler(logoutHandler()));
                return http.build();
    }
    private LogoutHandler logoutHandler() {
        return (request, response, authentication) -> {
            try {
                String baseUrl = "http://localhost:3000/";
                response.sendRedirect(issuer + "v2/logout?client_id=" + clientId + "&returnTo=" + baseUrl);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }
}