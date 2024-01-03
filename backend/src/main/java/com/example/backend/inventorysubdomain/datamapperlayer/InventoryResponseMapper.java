package com.example.backend.inventorysubdomain.datamapperlayer;

import com.example.backend.inventorysubdomain.datalayer.Inventory;
import com.example.backend.inventorysubdomain.presentationlayer.InventoryResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface InventoryResponseMapper {

    @Mappings({
            @Mapping(expression = "java(inventory.getShipmentIdentifier().getShipmentId())", target = "shipmentId"),
            @Mapping(expression = "java(inventory.getInventoryIdentifier().getInventoryId())", target = "inventoryId")
    })
    InventoryResponseModel entityToResponseModel(Inventory inventory);
}
