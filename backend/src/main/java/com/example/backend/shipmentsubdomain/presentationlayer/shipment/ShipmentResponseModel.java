package com.example.backend.shipmentsubdomain.presentationlayer.shipment;

import com.example.backend.shipmentsubdomain.datalayer.Address.Address;
import com.example.backend.shipmentsubdomain.datalayer.shipment.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder
public class ShipmentResponseModel {
    private String shipmentId;
    private Address pickupAddress;
    private Address destinationAddress;
    private String userId;
    private String truckId;
    private Status status;
    private String shipmentName;
    private double approximateWeight;
    private double weight;
    private String email;
    private String phoneNumber;
}
