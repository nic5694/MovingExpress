package com.example.backend.shipmentsubdomain.datalayer.shipment;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class TruckIdentifier {
    public String vin;
}
