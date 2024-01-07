package com.example.backend.inventorysubdomain.datamapperlayer;

import com.example.backend.inventorysubdomain.datalayer.Inventory;
import com.example.backend.inventorysubdomain.presentationlayer.InventoryRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface InventoryRequestMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "inventoryIdentifier", ignore = true),
            @Mapping(target = "shipmentIdentifier", ignore = true)
    })
    Inventory requestModelToEntity(InventoryRequestModel inventoryRequestModel);
}
