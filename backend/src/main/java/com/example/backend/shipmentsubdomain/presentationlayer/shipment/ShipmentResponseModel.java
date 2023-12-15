package com.example.backend.shipmentsubdomain.presentationlayer.shipment;

import com.example.backend.shipmentsubdomain.datalayer.Address.Address;
import com.example.backend.shipmentsubdomain.datalayer.shipment.ShipmentStatus;
import jakarta.annotation.Nullable;
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
    private String clientId;
    @Nullable
    private String truckId;
    private ShipmentStatus shipmentStatus;
}
