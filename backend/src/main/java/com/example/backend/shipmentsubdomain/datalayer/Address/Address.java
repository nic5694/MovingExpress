package com.example.backend.shipmentsubdomain.datalayer.Address;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Objects;

@Data
@Entity(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private AddressIdentifier addressIdentifier;
    private String city;
    private String streetAddress;
    private String country;
    private String postalCode;
    public Address(@NotNull String city, @NotNull String streetAddress, @NotNull String country, @NotNull String postalCode) {
        this.addressIdentifier = new AddressIdentifier();
        Objects.requireNonNull(this.city = city);
        Objects.requireNonNull(this.streetAddress = streetAddress);
        Objects.requireNonNull(this.country = country);
        Objects.requireNonNull(this.postalCode = postalCode);
    }

    @SuppressWarnings("unused")
    public Address() {
        this.addressIdentifier = new AddressIdentifier();
    }

    @Transient
    public String getAddressId() {
        return addressIdentifier.getAddressId();
    }
}
