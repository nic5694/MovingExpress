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
            @Mapping(expression = "java(quote.getPickupAddress().getPickupStreetAddress())", target = "pickupStreetAddress"),
            @Mapping(expression = "java(quote.getPickupAddress().getPickupCity())", target = "pickupCity"),
            @Mapping(expression = "java(quote.getPickupAddress().getPickupProvince())", target = "pickupProvince"),
            @Mapping(expression = "java(quote.getPickupAddress().getPickupCountry())", target = "pickupCountry"),
            @Mapping(expression = "java(quote.getPickupAddress().getPickupPostalCode())", target = "pickupPostalCode"),
            @Mapping(expression = "java(quote.getPickupAddress().getPickupRoomNumber())", target = "pickupRoomNumber"),
            @Mapping(expression = "java(quote.getPickupAddress().isPickupElevator())", target = "pickupElevator"),
            @Mapping(expression = "java(quote.getPickupAddress().getPickupBuildingType())", target = "pickupBuildingType"),
            @Mapping(expression = "java(quote.getDestinationAddress().getDestinationStreetAddress())", target = "destinationStreetAddress"),
            @Mapping(expression = "java(quote.getDestinationAddress().getDestinationCity())", target = "destinationCity"),
            @Mapping(expression = "java(quote.getDestinationAddress().getDestinationProvince())", target = "destinationProvince"),
            @Mapping(expression = "java(quote.getDestinationAddress().getDestinationCountry())", target = "destinationCountry"),
            @Mapping(expression = "java(quote.getDestinationAddress().getDestinationPostalCode())", target = "destinationPostalCode"),
            @Mapping(expression = "java(quote.getDestinationAddress().getDestinationRoomNumber())", target = "destinationRoomNumber"),
            @Mapping(expression = "java(quote.getDestinationAddress().isDestinationElevator())", target = "destinationElevator"),
            @Mapping(expression = "java(quote.getDestinationAddress().getDestinationBuildingType())", target = "destinationBuildingType"),
            @Mapping(expression = "java(quote.getContactDetails().getFirstName())", target = "firstName"),
            @Mapping(expression = "java(quote.getContactDetails().getLastName())", target = "lastName"),
            @Mapping(expression = "java(quote.getContactDetails().getPhoneNumber())", target = "phoneNumber"),
            @Mapping(expression = "java(quote.getContactDetails().getEmailAddress())", target = "emailAddress"),
            @Mapping(expression = "java(quote.getContactMethod())", target = "contactMethod"),
            @Mapping(expression = "java(quote.getInitiationDate())", target = "initiationDate"),
            @Mapping(expression = "java(quote.getExpectedMovingDate())", target = "expectedMovingDate"),
            @Mapping(expression = "java(quote.getComment())", target = "comment"),
            //@Mapping(expression = "java(quote.getMovingEstimatorIdentifier().getMovingEstimatorId())", target = "movingEstimatorId")
    })
    QuoteResponse entityToResponseModel(Quote quote);
}
