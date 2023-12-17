package com.example.backend.shipmentsubdomain.presentationlayer.shipment;

import com.example.backend.shipmentsubdomain.datalayer.Address.Address;
import com.example.backend.shipmentsubdomain.datalayer.shipment.ShipmentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder
public class ShipmentResponseModel {
    private String shipmentId;
    private Address departureAddress;
    private Address arrivalAddress;
    private String userId;
    private String truckId;
    private ShipmentStatus shipmentStatus;
    private String shipmentName;
    private double approximateWeight;
    private double weight;
    private String email;
}
