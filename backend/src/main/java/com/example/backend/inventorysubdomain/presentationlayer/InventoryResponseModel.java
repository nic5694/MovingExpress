package com.example.backend.inventorysubdomain.presentationlayer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder
public class InventoryResponseModel {
    String name;
    String inventoryId;
    String shipmentId;
    String description;
}
