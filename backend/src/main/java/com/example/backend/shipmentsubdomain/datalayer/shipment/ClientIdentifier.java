package com.example.backend.shipmentsubdomain.datalayer.shipment;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class ClientIdentifier {
    public String clientId;
    public ClientIdentifier(){
        this.clientId= java.util.UUID.randomUUID().toString();
    }
}
