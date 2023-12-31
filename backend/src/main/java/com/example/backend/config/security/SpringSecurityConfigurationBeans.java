package com.example.backend.config.security;

import com.example.backend.config.ErrorMessge;
import com.example.backend.config.security.csrf.SpaCsrfToken;
import com.example.backend.config.security.filters.CsrfCustomFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import lombok.Generated;
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
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Generated
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
                        .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/api/v1/movingexpress/quotes")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.POST, "/api/v1/movingexpress/quotes/request")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.POST, "/api/v1/movingexpress/quotes/*/events")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/api/v1/movingexpress/shipments")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/api/v1/movingexpress/shipments/*")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/api/v1/movingexpress/customers")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/api/v1/movingexpress/security/user-info}")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/api/v1/movingexpress/quotes/*")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.POST, "api/v1/movingexpress/shipments/*")).permitAll()
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
                })
                .csrf((csrf) -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .csrfTokenRequestHandler(new SpaCsrfToken())
                        .ignoringRequestMatchers(
                                new AntPathRequestMatcher("/api/v1/movingexpress/quotes/*/events", HttpMethod.POST.toString()),
                                new AntPathRequestMatcher("/api/v1/movingexpress/logout", HttpMethod.POST.toString()),
                                new AntPathRequestMatcher("/api/v1/movingexpress/security/redirect", HttpMethod.GET.toString()),
                                new AntPathRequestMatcher("/api/v1/movingexpress/quotes/request", HttpMethod.POST.toString()),
                                new AntPathRequestMatcher("/api/v1/movingexpress/shipments", HttpMethod.POST.toString()),
                                new AntPathRequestMatcher("/api/v1/movingexpress/customers", HttpMethod.GET.toString()),
                                new AntPathRequestMatcher("/api/v1/movingexpress/quotes", HttpMethod.GET.toString())

                        )
                )
                .cors(httpSecurityCorsConfigurer -> {
                    final var cors = new CorsConfiguration();
                    cors.setAllowedOrigins(List.of(frontendDomain));
                    cors.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
                    cors.setAllowedHeaders(Arrays.asList("authorization", "content-type", "xsrf-token"));
                    cors.setExposedHeaders(List.of("xsrf-token"));
                    cors.setAllowCredentials(true);
                    cors.setMaxAge(3600L);
                })
                .addFilterAfter(new CsrfCustomFilter(), BasicAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
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

            List<Cookie> cookies = List.of(request.getCookies());

            cookies.forEach(cookie -> {
                Cookie newCookie = new Cookie(cookie.getName(), "");
                newCookie.setMaxAge(0);
                newCookie.setPath("/");
                response.addCookie(newCookie);
            });

            boolean isSignup = Boolean.parseBoolean(request.getParameter("isLogoutSignUp"));
            boolean isExternal = Boolean.parseBoolean(request.getParameter("isLogoutExternal"));
            log.info("isSignup: " + isSignup);
            try {
                if (isSignup)
                    response.sendRedirect(issuer + "v2/logout?client_id=" + clientId + "&returnTo=http://localhost:8080/oauth2/authorization/okta");
                else if (isExternal)
                    response.sendRedirect(issuer + "v2/logout?client_id=" + clientId + "&returnTo=" + frontendDomain + "/external");
                else
                    response.sendRedirect(issuer + "v2/logout?client_id=" + clientId + "&returnTo=" + frontendDomain);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }

}

