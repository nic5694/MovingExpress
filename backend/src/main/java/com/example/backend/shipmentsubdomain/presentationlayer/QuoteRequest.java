package com.example.backend.shipmentsubdomain.presentationlayer;

import com.example.backend.shipmentsubdomain.datalayer.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.sql.Date;
import java.time.LocalDate;

@Value
@Builder
@AllArgsConstructor
public class QuoteRequest {
    private String pickupStreetAddress;
    private String pickupCity;
    private String pickupProvince;
    private Country pickupCountry;
    private String pickupPostalCode;
    private int pickupRoomNumber;
    private boolean pickupElevator;
    private String pickupBuildingType;
    private String destinationStreetAddress;
    private String destinationCity;
    private String destinationProvince;
    private Country destinationCountry;
    private String destinationPostalCode;
    private int destinationRoomNumber;
    private boolean destinationElevator;
    private String destinationBuildingType;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private LocalDate expectedMovingDate;
    private ContactMethod contactMethod;
    private String comment;
}
