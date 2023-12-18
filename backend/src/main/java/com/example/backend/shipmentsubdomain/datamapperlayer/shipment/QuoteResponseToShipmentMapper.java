package com.example.backend.shipmentsubdomain.datamapperlayer.shipment;
import lombok.Generated;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.example.backend.shipmentsubdomain.datalayer.shipment.Shipment;
import com.example.backend.shipmentsubdomain.presentationlayer.QuoteResponseModel;

@Generated
@Mapper(componentModel = "spring", uses = AddressMapper.class)
public interface QuoteResponseToShipmentMapper {

    //ignore all fields that are not mapped (id)

    @Mapping(target = "pickupAddress", expression = "java(addressMapper.toAddress(quoteResponse.getPickupStreetAddress(), quoteResponse.getPickupCity(), quoteResponse.getPickupPostalCode(), quoteResponse.getPickupCountry()))")
    @Mapping(target = "destinationAddress", expression = "java(addressMapper.toAddress(quoteResponse.getDestinationStreetAddress(), quoteResponse.getDestinationCity(), quoteResponse.getDestinationPostalCode(), quoteResponse.getDestinationCountry()))")
    @Mapping(expression = "java(quoteResponse.getExpectedMovingDate())", target = "expectedMovingDate")
    @Mapping(target = "phoneNumber", expression = "java(quoteResponse.getPhoneNumber())")
    @Mapping(target = "truckIdentifier", ignore = true)
    @Mapping(target = "userId", ignore = true)
    Shipment toShipment(QuoteResponseModel quoteResponse, AddressMapper addressMapper);
}