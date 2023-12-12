package com.example.backend.shipmentsubdomain.datalayer.shipment;

import jakarta.persistence.Embeddable;

@Embeddable
public class AddressIdentifier {
    public String addressId;
    public AddressIdentifier(){
        this.addressId= java.util.UUID.randomUUID().toString();
    }
}
