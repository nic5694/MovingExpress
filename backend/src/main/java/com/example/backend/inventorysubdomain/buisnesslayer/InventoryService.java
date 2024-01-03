package com.example.backend.inventorysubdomain.buisnesslayer;

import com.example.backend.inventorysubdomain.presentationlayer.InventoryRequestModel;
import com.example.backend.inventorysubdomain.presentationlayer.InventoryResponseModel;

public interface InventoryService {
    InventoryResponseModel addInventory(InventoryRequestModel inventoryRequestModel, String shipmentId);

}
