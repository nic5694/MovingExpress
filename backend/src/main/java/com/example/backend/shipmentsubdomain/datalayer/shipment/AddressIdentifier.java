package com.example.backend.shipmentsubdomain.datalayer.shipment;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class AddressIdentifier {
    private String addressId;
    private AddressIdentifier(){
        this.addressId= java.util.UUID.randomUUID().toString();
    }

    private String getAddressId() {
        return addressId;
    }
}
