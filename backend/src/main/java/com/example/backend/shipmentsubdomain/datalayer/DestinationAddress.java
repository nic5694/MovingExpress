package com.example.backend.shipmentsubdomain.datalayer;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DestinationAddress {
    private String destinationStreetAddress;
    private String destinationCity;
    private String destinationProvince;
    private Country destinationCountry;
    private String destinationPostalCode;
    private int destinationRoomNumber;
    private boolean destinationElevator;
    private String destinationBuildingType;
}
