package com.example.backend.shipmentsubdomain.datalayer;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactDetails {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
}
