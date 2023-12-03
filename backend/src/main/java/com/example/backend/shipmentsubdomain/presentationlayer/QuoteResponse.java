package com.example.backend.shipmentsubdomain.presentationlayer;

import com.example.backend.shipmentsubdomain.datalayer.ContactMethod;

import java.sql.Date;

import com.example.backend.shipmentsubdomain.datalayer.Country;
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
    private String phoneNumber;
    private String emailAddress;
    private ContactMethod contactMethod;
    private Date date;
    private String comment;
}
