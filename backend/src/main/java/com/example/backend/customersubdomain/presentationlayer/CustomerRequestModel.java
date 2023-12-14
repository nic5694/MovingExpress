package com.example.backend.customersubdomain.presentationlayer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerRequestModel {
    @NotBlank
    String clientId;
    String profilePictureUrl;
    @Email
    @NotBlank
    String email;
    @NotBlank
    String firstName;
    String lastName;
    String phoneNumber;
    String streetAddress;
    String city;
    String province;
    String country;
    String postalCode;
}
