package com.example.backend.shipmentsubdomain.datalayer;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ContactDetails {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;
}
