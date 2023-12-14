package com.example.backend.customersubdomain.presentationlayer;

import com.example.backend.customersubdomain.buisnesslayer.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/movingexpress/customers")
public class CustomerController {
    private final CustomerService customerService;

    @DeleteMapping
    public ResponseEntity<Void> deleteCustomer(@AuthenticationPrincipal OidcUser principal) {
        String userId = principal.getSubject();
        log.info("Delete customer with userId: {}", userId);

        customerService.deleteCustomer(userId);

        return ResponseEntity.noContent().build();

    }


    @GetMapping
    public ResponseEntity<CustomerResponseModel> getCustomerByUserId(@AuthenticationPrincipal OidcUser principal, @RequestParam Map<String, String> requestParams) {
        log.info("Get customer with userId: {}", principal.getSubject());

        if (requestParams.containsKey("simpleCheck") && requestParams.get("simpleCheck").equals("true")) {
            if(customerService.checkIfCustomerExists(principal.getSubject()))
                return ResponseEntity.ok().build();
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        CustomerResponseModel customerResponse = customerService.getCustomerByUserId(principal.getSubject());

        return ResponseEntity.ok(customerResponse);

    }


    @PostMapping
    public ResponseEntity<CustomerResponseModel> addCustomer(@AuthenticationPrincipal OidcUser principal,
                                                             @Valid @RequestBody CustomerRequestModel customerRequest) {
        String userId = principal.getSubject();
        log.info("Create customer with userId: {}", userId);

        CustomerResponseModel customerResponse = customerService.addCustomer(customerRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(customerResponse);

    }

    @PutMapping
    public ResponseEntity<CustomerResponseModel> updateCustomer(@AuthenticationPrincipal OidcUser principal,
                                                           @Valid @RequestBody CustomerRequestModel customerRequest) {
        String userId = principal.getSubject();
        log.info("Update customer with userId: {}", userId);

        CustomerResponseModel customerResponse = customerService.updateCustomer(customerRequest, userId);

        return ResponseEntity.ok(customerResponse);

    }
}
