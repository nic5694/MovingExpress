package com.example.backend.customersubdomain.presentationlayer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerResponseModel {
    String userId;
    String profilePictureUrl;
    String email;
    String firstName;
    String lastName;
    String phoneNumber;
    String streetAddress;
    String city;
    String country;
    String postalCode;
}
