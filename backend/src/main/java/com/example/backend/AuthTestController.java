package com.example.backend;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.GrantedAuthority;

import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path="api")
public class AuthTestController {
    @GetMapping(value = "/public")
    public String publicEndpoint() {
        return "All good. You DO NOT need to be authenticated to call /api/public.";
    }

    @GetMapping(value = "/private")
    public String privateEndpoint(@AuthenticationPrincipal OidcUser principal){
        return "All good. You can see this because you are Authenticated. Your name is " + principal.getFullName() + " and your email is " + principal.getEmail();
    }

    @GetMapping(value = "/info")
    public String GetGalleryById(@AuthenticationPrincipal OidcUser user){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toSet());

        return "Hello, " + user.getFullName() + "!<br/><br/>Authorities: " + authorities;
    }

    @GetMapping(value = "/private-scoped")
    @PreAuthorize("hasAuthority('SCOPE_read:shipmentquotes')")
    public String privateScopedEndpoint() {
        return "All good. You can see this because you are Authenticated with a Token granted the 'read:messages' scope";
    }
}
