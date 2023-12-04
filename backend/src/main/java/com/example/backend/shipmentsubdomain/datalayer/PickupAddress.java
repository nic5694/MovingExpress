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
    private String pickupStreetAddress;
    private String pickupCity;
    private String pickupProvince;
    private Country pickupCountry;
    private String pickupPostalCode;
    private int pickupRoomNumber;
    private boolean pickupElevator;
    private String pickupBuildingType;
}
