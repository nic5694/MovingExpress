package com.example.backend.config.security.service;

import lombok.Generated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.net.URI;
@Slf4j
@Service
@Generated
@RequiredArgsConstructor
public class Auth0LoginService {
    public ResponseEntity<Void> getVoidResponseEntity(@AuthenticationPrincipal OidcUser principal) {
        HttpHeaders headers = new HttpHeaders();
        ResponseCookie cookie = ResponseCookie.from("id_token",principal.getIdToken().getTokenValue())
                .httpOnly(true)
                .secure(true)
                .path("/")
                .sameSite("None")
                .build();
        ResponseCookie authCookie = ResponseCookie.from("isAuthenticated", "true")
                .httpOnly(false)
                .secure(true)
                .path("/")
                .sameSite("None")
                .build();
        ResponseCookie accessPermissionCookie = ResponseCookie.from("accessPermission", principal.getAuthorities().toString()
                        .replace(",","-").replace(" ",""))
                .httpOnly(false)
                .secure(true)
                .path("/")
                .sameSite("None")
                .build();
        String picture = principal.getClaim("picture");

        ResponseCookie pictureCookie = ResponseCookie.from("picture", picture)
                .httpOnly(false)
                .secure(true)
                .path("/")
                .sameSite("None")
                .build();


        log.info("Subject: " + principal.getSubject());
        headers.add(HttpHeaders.SET_COOKIE, cookie.toString());
        headers.add(HttpHeaders.SET_COOKIE, authCookie.toString());
        headers.add(HttpHeaders.SET_COOKIE, accessPermissionCookie.toString());
        headers.add(HttpHeaders.SET_COOKIE, pictureCookie.toString());

        if (principal.getClaim("https://movingexpress.com/roles").toString().contains("Shipment_Reviewer")) {
            return ResponseEntity.status(HttpStatus.FOUND).headers(headers)
                    .location(URI.create("http://localhost:3000/Reviewer")).build();
        } else if (principal.getClaim("https://movingexpress.com/roles").toString().contains("Shipment_Estimator")) {
            return ResponseEntity.status(HttpStatus.FOUND).headers(headers)
                    .location(URI.create("http://localhost:3000/Estimator")).build();
        } else {
            return ResponseEntity.status(HttpStatus.FOUND).headers(headers)
                    .location(URI.create("http://localhost:3000/Home")).build();
        }
    }
}
