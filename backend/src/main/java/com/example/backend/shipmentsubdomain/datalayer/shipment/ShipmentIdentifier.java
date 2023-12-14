package com.example.backend.shipmentsubdomain.datalayer.shipment;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.UUID;

@Embeddable
@Getter
public class ShipmentIdentifier {
    private String shipmentId;

    public ShipmentIdentifier(){
        this.shipmentId= UUID.randomUUID().toString();
    }
}
