package com.example.backend.shipmentsubdomain.datalayer.shipment;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class AddressIdentifier {
    private String addressId;
    public AddressIdentifier(){
        this.addressId= java.util.UUID.randomUUID().toString();
    }

    public String getAddressId() {
        return addressId;
    }
}
