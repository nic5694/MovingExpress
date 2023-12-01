package com.example.backend.config.auth;
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

//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig{
//    @Value("${okta.oauth2.issuer}")
//    private String issuer;
//    @Value("${okta.oauth2.client-id}")
//    private String clientId;
//    @Bean
//    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((authorize) -> authorize
//                        .requestMatchers(AntPathRequestMatcher.antMatcher("/api/v1/private")).authenticated()
//                        .anyRequest().authenticated()
//                )
//                .cors(withDefaults())
//                .oauth2ResourceServer(oauth2 -> oauth2
//                        .jwt(withDefaults())
//                )
//                .logout(logout -> logout
//                        .addLogoutHandler(logoutHandler()));
//                return http.build();
//    }

//    }
//}

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class SecurityConfig {
//    @Value("${okta.oauth2.issuer}")
//    private String issuer;
//    @Value("${okta.oauth2.client-id}")
//
    private final String begginingFrontendUrl = "http://localhost:3000/";
    private final ObjectMapper objectMapper;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /*
        This is where we configure the security required for our endpoints and setup our app to serve as
        an OAuth2 Resource Server, using JWT validation.
        */
        http
                .authorizeHttpRequests((authorize) -> authorize
                        //endpoint with the required authorities
                        .requestMatchers("/api/public").permitAll()
                        .requestMatchers("/api/private").authenticated()
                        .requestMatchers("/api/private-scoped").hasAuthority("SCOPE_read:shipmentquotes")
                        .anyRequest().authenticated()
                )
                .exceptionHandling(exceptionHandling -> {
                    exceptionHandling.authenticationEntryPoint(authenticationEntryPoint());
                })
                .oauth2ResourceServer(jwt -> jwt.jwt(withDefaults()))
                .cors(cors -> {
                    cors.configurationSource(corsConfigurationSource());
                })
                .csrf(httpSecurityCsrfConfigurer -> {
                    httpSecurityCsrfConfigurer.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.POST,"/api/v1/corso/logout"));
                });
        return http.build();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin(Arrays.toString(new String[]{"http://localhost:3000","http://localhost:8080"}));
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
//        config.addAllowedHeader("*");
        config.addAllowedHeader("Authorization");
        source.registerCorsConfiguration("/**", config);
        return source;
    }
    private AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            log.error("Authentication error: {}", authException.getMessage());
            log.error("Authentication error: {}", authException.getLocalizedMessage());
            String authorizationHeader = request.getHeader("Authorization");
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                String jwtToken = authorizationHeader.substring(7); // Assuming that berarer is 7 characters long and there is a space after it
                log.info("JWT Token: {}", jwtToken);
            }

            // Retrieve cookies from the request
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    log.info("Cookie - Name: {}, Value: {}", cookie.getName(), cookie.getValue());
                }
            }

            final ErrorMessage errorMessage = ErrorMessage.from("Requires authentication");
            final String json = objectMapper.writeValueAsString(errorMessage);

            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(json);
            response.flushBuffer();
        };
    }

}