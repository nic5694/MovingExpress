package com.example.backend.inventorysubdomain.presentationlayer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@AllArgsConstructor
@Value
@Builder
public class InventoryRequestModel {
    String name;
    String shipmentId;
    String description;
}
