package com.example.backend.shipmentsubdomain.datalayer.shipment;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.Getter;

@Data
@Embeddable
public class TruckIdentifier {
    private String vin;
}
