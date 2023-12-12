package com.example.backend.shipmentsubdomain.businesslayer;

import com.example.backend.shipmentsubdomain.datalayer.*;
import com.example.backend.shipmentsubdomain.datamapperlayer.QuoteRequestMapper;
import com.example.backend.shipmentsubdomain.datamapperlayer.QuoteResponseMapper;
import com.example.backend.shipmentsubdomain.presentationlayer.QuoteRequest;
import com.example.backend.shipmentsubdomain.presentationlayer.QuoteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService{
    private final QuoteRepository quoteRepository;
    private final QuoteRequestMapper quoteRequestMapper;
    private final QuoteResponseMapper quoteResponseMapper;

    @Override
    public QuoteResponse addQuote(QuoteRequest quoteRequest) {
        Quote quote=quoteRequestMapper.requestModelToEntity(quoteRequest);

        PickupAddress pickupAddress = new PickupAddress(quoteRequest.getPickupStreetAddress(),
                quoteRequest.getPickupCity(),
                quoteRequest.getPickupCountry(),
                quoteRequest.getPickupPostalCode(),
                quoteRequest.getPickupNumberOfRooms(),
                quoteRequest.isPickupElevator(),
                quoteRequest.getPickupBuildingType());

        DestinationAddress destinationAddress = new DestinationAddress(quoteRequest.getDestinationStreetAddress(),
                quoteRequest.getDestinationCity(),
                quoteRequest.getDestinationCountry(),
                quoteRequest.getDestinationPostalCode(),
                quoteRequest.getDestinationNumberOfRooms(),
                quoteRequest.isDestinationElevator(),
                quoteRequest.getDestinationBuildingType());

        ContactDetails contactDetails = new ContactDetails(quoteRequest.getFirstName(),
                quoteRequest.getLastName(),
                quoteRequest.getEmailAddress(),
                quoteRequest.getPhoneNumber());

        quote.setPickupAddress(pickupAddress);
        quote.setDestinationAddress(destinationAddress);
        quote.setContactDetails(contactDetails);
        quote.setInitiationDate(LocalDateTime.now());
        quote.setQuoteStatus(QuoteStatus.CREATED);

        Quote savedQuote=quoteRepository.save(quote);
        return quoteResponseMapper.entityToResponseModel(savedQuote);
    }
}
