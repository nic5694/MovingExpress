package com.example.backend.customersubdomain.datalayer;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Table(name = "customers")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    private String userId;
    @Nullable
    private String profilePictureUrl;
    private String firstName;
    @Nullable
    private String lastName;
    private String email;
    @Nullable
    private String phoneNumber;
    @Nullable
    private String streetAddress;
    @Nullable
    private String city;
    @Nullable
    private String province;
    @Nullable
    private String country;
    @Nullable
    private String postalCode;
}
