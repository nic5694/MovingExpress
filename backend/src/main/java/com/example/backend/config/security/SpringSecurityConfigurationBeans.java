package com.example.backend.config.security;

import com.example.backend.config.ErrorMessge;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.IOException;
import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SpringSecurityConfigurationBeans {
    @Value("${okta.oauth2.issuer}")
    private String issuer;
    @Value("${okta.oauth2.client-id}")
    private String clientId;
    private final String frontendDomain = "http://localhost:3000";

    private final ObjectMapper objectMapper;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.POST, "/api/v1/movingexpress/quotes/request")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/api/v1/movingexpress/quotes/retrieve")).permitAll()
                        .anyRequest().permitAll()
                )
                .exceptionHandling(exceptionHandling -> {
                    exceptionHandling.authenticationEntryPoint(authenticationEntryPoint());
                })
                .oauth2Login(httpSecurityOAuth2LoginConfigurer -> {
                    httpSecurityOAuth2LoginConfigurer.loginPage("/login/oauth2/code/okta")
                            .defaultSuccessUrl("http://localhost:8080/api/v1/movingexpress/security/redirect", true)
                            .permitAll();
                })
                .oauth2ResourceServer(jwt -> jwt.jwt(withDefaults()))
                .logout(logout -> {
                    logout
                            .logoutUrl("/api/v1/movingexpress/logout")
                            .addLogoutHandler(logoutHandler())
                            .logoutSuccessHandler((request, response, authentication) -> {

                                Arrays.stream(request.getCookies()).toList().forEach(cookie -> {
                                    if (!cookie.getName().equals("JSESSIONID")) {
                                        Cookie newCookie = new Cookie(cookie.getName(), "");
                                        newCookie.setMaxAge(0);
                                        newCookie.setPath("/");
                                        response.addCookie(newCookie);

                                    }
                                });
                                response.setStatus(HttpStatus.OK.value());
                            });
                }).csrf().disable();
                //.ignoringRequestMatchers("/api/v1/movingexpress/quotes/request");
//                .csrf( -> {
//                    httpSecurityCsrfConfigurer.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.POST, "/api/v1/movingexpress/logout"));
//                });
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*"); // Allow requests from any origin
//        config.addAllowedOrigin(Arrays.toString(new String[]{"http://localhost:3000", "http://localhost:8080"}));
        config.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:8080"));
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedHeader("*");
        config.addAllowedHeader("Authorization");
        config.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    private AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            final ErrorMessge errorMessage = ErrorMessge.from("Requires authentication");
            final String json = objectMapper.writeValueAsString(errorMessage);

            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(json);
            response.flushBuffer();
        };
    }

    private LogoutHandler logoutHandler() {
        return (request, response, authentication) -> {
            try {
                response.sendRedirect(issuer + "v2/logout?client_id=" + clientId + "&returnTo=" + frontendDomain);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }

}

