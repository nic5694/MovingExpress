package com.example.backend.clientsubdomain.presentationlayer;

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
    @Email
    @NotBlank
    String email;
    String firstName;
    @NotBlank
    String lastName;
    @NotBlank
    String phone;
    @NotBlank
    String streetAddress;
    @NotBlank
    String city;
    @NotBlank
    String province;
    @NotBlank
    String country;
    @NotBlank
    String postalCode;


}
