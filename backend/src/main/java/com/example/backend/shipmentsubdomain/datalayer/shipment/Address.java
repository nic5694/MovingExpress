package com.example.backend.shipmentsubdomain.datalayer.shipment;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    @Embedded
    public AddressIdentifier addressIdentifier;
    private String city;
    private String streetAddress;
    private String province;
    private String country;
    private String postalCode;
    public Address(@NotNull String city, @NotNull String streetAddress, @NotNull String province, @NotNull String country, @NotNull String postalCode) {
        Objects.requireNonNull(this.city = city);
        Objects.requireNonNull(this.streetAddress = streetAddress);
        Objects.requireNonNull(this.province = province);
        Objects.requireNonNull(this.country = country);
        Objects.requireNonNull(this.postalCode = postalCode);
    }

    @SuppressWarnings("unused")
    public Address() {}
}
