package com.example.backend.clientsubdomain.presentationlayer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerResponseModel {
    String email;
    String firstName;
    String lastName;
    String phone;
    String address;
    String city;
    String province;
    String country;
    String postalCode;
}
