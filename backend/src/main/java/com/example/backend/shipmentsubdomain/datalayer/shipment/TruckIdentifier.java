package com.example.backend.shipmentsubdomain.datalayer.shipment;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@Getter
@Embeddable
public class TruckIdentifier {
    private String vin;

    public TruckIdentifier(){
        this.vin= UUID.randomUUID().toString();
    }
}
