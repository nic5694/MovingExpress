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
            @Mapping(expression = "java(shipment.getDepartureAddress())", target = "departureAddress"),
            @Mapping(expression = "java(shipment.getDepartureAddress())", target = "arrivalAddress"),
            @Mapping(expression = "java(shipment.getUserId())", target = "clientId"),
            @Mapping(expression = "java(shipment.getTruckIdentifier().getVin())", target = "truckId")
    })
    ShipmentResponseModel entityToResponseModel(Shipment shipment);

}
