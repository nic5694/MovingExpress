package com.example.backend.config.security.controllers;

import com.example.backend.config.security.data.UserInfoResponseModel;
import com.example.backend.config.security.service.Auth0LoginService;
import com.example.backend.config.security.service.Auth0ManagementService;
import com.example.backend.customersubdomain.buisnesslayer.CustomerService;
import com.example.backend.customersubdomain.presentationlayer.CustomerRequestModel;
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
    private final CustomerService customerService;
    @GetMapping("/redirect")
    public ResponseEntity<Void> redirectAfterLogin(@AuthenticationPrincipal OidcUser principal) throws IOException, InterruptedException {
        if (principal == null) {
            log.info("Principal is null");
            return ResponseEntity.status(HttpStatus.FOUND)
                    .location(URI.create("http://localhost:8080/oauth2/authorization/okta")).build();
        }
        log.info("Principal is not null, userId: {}", principal.getSubject());
        if(customerService.checkIfCustomerExists(principal.getSubject())){
            log.info("Customer already exists");
//            return ResponseEntity.status(HttpStatus.FOUND)
//                    .location(URI.create("http://localhost:3000/external")).build();
        } else {
            log.info("Customer does not exist");
            CustomerRequestModel customerRequestModel = CustomerRequestModel.builder()
                    .userId(principal.getSubject())
                    .email(principal.getEmail())
                    .firstName(principal.getName())
                    .profilePictureUrl(principal.getClaim("picture"))
                    .build();
            customerService.addCustomer(customerRequestModel);
            log.info("Added the customer with userId: {}", principal.getSubject());
        }
        if(!customerService.checkIfCustomerExists(principal.getSubject())){
            CustomerRequestModel customerRequestModel = CustomerRequestModel.builder()
                    .userId(principal.getSubject())
                    .email(principal.getEmail())
                    .firstName(principal.getName())
                    .profilePictureUrl(principal.getClaim("picture"))
                    .build();
            customerService.addCustomer(customerRequestModel);
            log.info("Added the customer with userId: {}", principal.getSubject());
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
