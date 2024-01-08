package com.example.backend.inventorysubdomain.datalayer;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.UUID;

@Embeddable

public class InventoryIdentifier {
    @Getter
    private String inventoryId;
    public InventoryIdentifier(){
        this.inventoryId = UUID.randomUUID().toString();
    }
}
