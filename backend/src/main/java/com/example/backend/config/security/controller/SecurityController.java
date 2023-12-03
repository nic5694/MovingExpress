package com.example.backend.config.security.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;


@CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
@RestController
@RequestMapping("/api/v1/movingexpress/security")
public class SecurityController {
    @RequestMapping("/redirect")
    public ResponseEntity<Void> redirectAfterLogin(@AuthenticationPrincipal OidcUser principal){

        ResponseCookie cookie = ResponseCookie.from("id_token",principal.getIdToken().getTokenValue())
                .httpOnly(true)
                .secure(true)
                .path("/")
                .sameSite("None")
                .build();

        return ResponseEntity.status(HttpStatus.FOUND).header(HttpHeaders.SET_COOKIE,cookie.toString()).location(URI.create("http://localhost:3000")).build();
    }
}