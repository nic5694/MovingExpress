package com.example.backend.inventorysubdomain.datalayer;

import com.example.backend.shipmentsubdomain.datalayer.shipment.ShipmentIdentifier;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity(name = "inventories")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Embedded
    private InventoryIdentifier inventoryIdentifier;
    @Embedded
    private ShipmentIdentifier shipmentIdentifier;
    private String name;
    @Nullable
    private String description;

    public Inventory(ShipmentIdentifier shipmentIdentifier, String name, @Nullable String description) {
        this.inventoryIdentifier = new InventoryIdentifier();
        this.shipmentIdentifier = shipmentIdentifier;
        this.name = name;
        this.description = description;
    }
}
