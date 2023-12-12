package com.example.backend.shipmentsubdomain.presentationlayer;

import com.example.backend.shipmentsubdomain.datalayer.ContactMethod;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.backend.shipmentsubdomain.datalayer.Country;
import com.example.backend.shipmentsubdomain.datalayer.QuoteStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder
public class QuoteResponse {
    private String quoteId;
    private String pickupStreetAddress;
    private String pickupCity;
    private Country pickupCountry;
    private String pickupPostalCode;
    private int pickupNumberOfRooms;
    private boolean pickupElevator;
    private String pickupBuildingType;
    private String destinationStreetAddress;
    private String destinationCity;
    private Country destinationCountry;
    private String destinationPostalCode;
    private int destinationNumberOfRooms;
    private boolean destinationElevator;
    private String destinationBuildingType;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;
    private ContactMethod contactMethod;
    private LocalDate expectedMovingDate;
    private LocalDateTime initiationDate;
    private String comment;
    private QuoteStatus quoteStatus;
    private String shipmentName;
}
