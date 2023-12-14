package com.example.backend.config.security.controllers;

import com.example.backend.config.security.data.UserInfoResponseModel;
import com.example.backend.config.security.service.Auth0LoginService;
import com.example.backend.config.security.service.Auth0ManagementService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
@CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/v1/movingexpress/security")
public class SecurityController {
    private final Auth0LoginService auth0LoginService;
    private final Auth0ManagementService auth0ManagementService;
    @GetMapping("/redirect")
    public ResponseEntity<Void> redirectAfterLogin(@AuthenticationPrincipal OidcUser principal) throws IOException, InterruptedException {
        String accessToken;
        if (principal == null) {
            log.info("Principal is null");
            return ResponseEntity.status(HttpStatus.FOUND)
                    .location(URI.create("http://localhost:8080/oauth2/authorization/okta")).build();
        }
        //handle apple, google and facebook login
        if (principal.getSubject().contains("apple") || principal.getSubject().contains("google-oauth2")
                || principal.getSubject().contains("facebook")) {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .location(URI.create("http://localhost:3000/external")).build();
        }
        if (principal.getSubject().contains("auth0")) {
            log.info("Pricipal claims: " + principal.getClaims().toString());
            if (!principal.getClaims().containsKey("https://movingexpress.com/roles")
                    || principal.getClaim("https://movingexpress.com/roles").toString().equals("[]")) {
                return ResponseEntity.status(HttpStatus.FOUND)
                        .location(URI.create("http://localhost:3000/logout")).build();
            }
        }
        log.info("Authorities: " + principal.getAuthorities().toString().replace(",", "-"));
        return auth0LoginService.getVoidResponseEntity(principal);
    }


    @GetMapping("/user-info")
    public ResponseEntity<UserInfoResponseModel> getUserInfo(@AuthenticationPrincipal OidcUser principal) throws IOException, InterruptedException {

        if(principal == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();


        String userId = principal.getSubject();

        return ResponseEntity.ok().body(auth0ManagementService.getUserInfo(userId));
    }
}
