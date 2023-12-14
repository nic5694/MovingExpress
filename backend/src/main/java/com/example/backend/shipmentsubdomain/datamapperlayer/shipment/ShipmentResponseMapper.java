//package com.example.backend.shipmentsubdomain.datamapperlayer.shipment;
//
//import com.example.backend.shipmentsubdomain.datalayer.shipment.Shipment;
//import com.example.backend.shipmentsubdomain.presentationlayer.shipment.ShipmentResponseModel;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.Mappings;
//
//@Mapper(componentModel="spring")
//public interface ShipmentResponseMapper {
//    @Mappings({
//            @Mapping(expression = "java(shipment.getShipmentIdentifier().getShipmentId())", target = "shipmentId"),
//            @Mapping(expression = "java(shipment.getDepartureAddressId().getAddressId())", target = "departureAddressId"),
//            @Mapping(expression = "java(shipment.getArrivalAddressId().getAddressId())", target = "arrivalAddressId"),
//            @Mapping(expression = "java(shipment.getClientId().getClientId())", target = "clientId"),
//            @Mapping(expression = "java(shipment.getTruckId().getTruckId())", target = "truckId")
//    })
//    ShipmentResponseModel entityToResponseModel(Shipment shipment);
//
//}
