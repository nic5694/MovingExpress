package com.example.backend;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping(path="api/v1/movingexpress")
public class AuthTestController {
    @GetMapping(value = "/public")
    public String publicEndpoint() {
        return "All good. You DO NOT need to be authenticated to call /api/public.";
    }
    @GetMapping(value = "/private")
    @PreAuthorize("hasAuthority('Shipment_Reviewer')")
    public String privateScopedEndpoint(@AuthenticationPrincipal OidcUser principal) {
        return "All good. You can see this because you are Authenticated with the role Shipment Reviewer.";
    }
}
