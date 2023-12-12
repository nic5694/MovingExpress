package com.example.backend.shipmentsubdomain.presentationlayer.shipment;

import com.example.backend.shipmentsubdomain.datalayer.shipment.ShipmentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder
public class ShipmentRequestModel {
    private String departureAddressId;
    private String arrivalAddressId;
    private String clientId;
    private String truckId;
    private ShipmentStatus shipmentStatus;
}
