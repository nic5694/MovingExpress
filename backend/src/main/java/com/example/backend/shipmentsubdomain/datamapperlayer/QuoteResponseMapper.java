package com.example.backend.shipmentsubdomain.datamapperlayer;

import com.example.backend.shipmentsubdomain.datalayer.Quote;
import com.example.backend.shipmentsubdomain.presentationlayer.QuoteResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface QuoteResponseMapper {
    @Mappings({
            @Mapping(expression = "java(quote.getQuoteIdentifier().getQuoteId())", target = "quoteId"),
            @Mapping(expression = "java(quote.getPickupAddress().getStreetAddress())", target = "pickupStreetAddress"),
            @Mapping(expression = "java(quote.getPickupAddress().getCity())", target = "pickupCity"),
            @Mapping(expression = "java(quote.getPickupAddress().getProvince())", target = "pickupProvince"),
            @Mapping(expression = "java(quote.getPickupAddress().getCountry())", target = "pickupCountry"),
            @Mapping(expression = "java(quote.getPickupAddress().getPostalCode())", target = "pickupPostalCode"),
            @Mapping(expression = "java(quote.getPickupAddress().getRoomNumber())", target = "pickupRoomNumber"),
            @Mapping(expression = "java(quote.getPickupAddress().isElevator())", target = "pickupElevator"),
            @Mapping(expression = "java(quote.getPickupAddress().getBuildingType())", target = "pickupBuildingType"),
            @Mapping(expression = "java(quote.getDestinationAddress().getStreetAddress())", target = "destinationStreetAddress"),
            @Mapping(expression = "java(quote.getDestinationAddress().getCity())", target = "destinationCity"),
            @Mapping(expression = "java(quote.getDestinationAddress().getProvince())", target = "destinationProvince"),
            @Mapping(expression = "java(quote.getDestinationAddress().getCountry())", target = "destinationCountry"),
            @Mapping(expression = "java(quote.getDestinationAddress().getPostalCode())", target = "destinationPostalCode"),
            @Mapping(expression = "java(quote.getDestinationAddress().getRoomNumber())", target = "destinationRoomNumber"),
            @Mapping(expression = "java(quote.getDestinationAddress().isElevator())", target = "destinationElevator"),
            @Mapping(expression = "java(quote.getDestinationAddress().getBuildingType())", target = "destinationBuildingType"),
            @Mapping(expression = "java(quote.getContactDetails().getFirstName())", target = "firstName"),
            @Mapping(expression = "java(quote.getContactDetails().getLastName())", target = "lastName"),
            @Mapping(expression = "java(quote.getContactDetails().getPhoneNumber())", target = "phoneNumber"),
            @Mapping(expression = "java(quote.getContactDetails().getEmailAddress())", target = "emailAddress"),
            @Mapping(expression = "java(quote.getContactMethod())", target = "contactMethod"),
            @Mapping(expression = "java(quote.getDate())", target = "date"),
            @Mapping(expression = "java(quote.getComment())", target = "comment")
    })
    QuoteResponse entityToResponseModel(Quote quote);
}
