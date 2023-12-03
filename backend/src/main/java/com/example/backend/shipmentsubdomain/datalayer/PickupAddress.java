package com.example.backend.shipmentsubdomain.datalayer;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PickupAddress {
    private String streetAddress;
    private String city;
    private String province;
    private Country country;
    private String postalCode;
    private int roomNumber;
    private boolean elevator;
    private String buildingType;
}
