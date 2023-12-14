package com.example.backend.clientsubdomain.datalayer;

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
    private String firstName;
    @Nullable
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String province;
    private String country;
    private String postalCode;
}
