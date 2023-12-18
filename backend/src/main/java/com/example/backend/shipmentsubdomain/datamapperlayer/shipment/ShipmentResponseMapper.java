package com.example.backend.shipmentsubdomain.datamapperlayer.shipment;

import com.example.backend.shipmentsubdomain.datalayer.shipment.Shipment;
import com.example.backend.shipmentsubdomain.presentationlayer.shipment.ShipmentResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel="spring")
public interface ShipmentResponseMapper {
    @Mappings({
            @Mapping(expression = "java(shipment.getShipmentIdentifier().getShipmentId())", target = "shipmentId"),
            @Mapping(expression = "java(shipment.getPickupAddress())", target = "pickupAddress"),
            @Mapping(expression = "java(shipment.getDestinationAddress())", target = "destinationAddress"),
            @Mapping(expression = "java(shipment.getUserId())", target = "userId"),
            @Mapping(source = "truckIdentifier.vin", target = "truckId"),
            @Mapping(expression = "java(shipment.getName())", target = "shipmentName"),
            @Mapping(expression = "java(shipment.getApproximateWeight())", target = "approximateWeight"),
            @Mapping(expression = "java(shipment.getWeight())", target = "weight"),
            @Mapping(expression = "java(shipment.getStatus())", target = "status"),
            @Mapping(expression = "java(shipment.getEmail())", target = "email"),
    })
    ShipmentResponseModel entityToResponseModel(Shipment shipment);
}
