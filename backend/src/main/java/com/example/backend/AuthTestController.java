package com.example.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/v1/")
public class AuthTestController {
    @GetMapping(value = "/public")
    public String publicEndpoint() {
        return "All good. You can see this because you are not authenticated.";
    }

    @GetMapping(value = "/private")
    public String privateEndpoint() {
        return "You can only see this because you are authenticated\n<a href=\"http://localhost:8080/logout\">Logout</a>";
    }
}
