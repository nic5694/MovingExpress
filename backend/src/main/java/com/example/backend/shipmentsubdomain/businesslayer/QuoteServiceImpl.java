package com.example.backend.shipmentsubdomain.businesslayer;

import com.example.backend.shipmentsubdomain.datalayer.*;
import com.example.backend.shipmentsubdomain.datamapperlayer.quote.QuoteRequestMapper;
import com.example.backend.shipmentsubdomain.datamapperlayer.quote.QuoteResponseMapper;
import com.example.backend.shipmentsubdomain.exceptions.NotFoundException;
import com.example.backend.shipmentsubdomain.presentationlayer.QuoteController;
import com.example.backend.shipmentsubdomain.presentationlayer.QuoteRequestModel;
import com.example.backend.shipmentsubdomain.presentationlayer.QuoteResponseModel;
import com.example.backend.shipmentsubdomain.presentationlayer.event.EventResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService {
    private final QuoteRepository quoteRepository;
    private final QuoteRequestMapper quoteRequestMapper;
    private final QuoteResponseMapper quoteResponseMapper;

    @Override
    public QuoteResponseModel getQuote(String quoteId) {
        Quote existingQuote = quoteRepository.findByQuoteIdentifier_QuoteId(quoteId);
        if (existingQuote == null) {
            throw new NotFoundException("quoteId not found: " + quoteId);
        }

        return quoteResponseMapper.entityToResponseModel(existingQuote);
    }

    @Override
    public QuoteResponseModel addQuote(QuoteRequestModel quoteRequestModel) {
        Quote quote = quoteRequestMapper.requestModelToEntity(quoteRequestModel);

        PickupAddress pickupAddress = new PickupAddress(quoteRequestModel.getPickupStreetAddress(),
                quoteRequestModel.getPickupCity(),
                quoteRequestModel.getPickupCountry(),
                quoteRequestModel.getPickupPostalCode(),
                quoteRequestModel.getPickupNumberOfRooms(),
                quoteRequestModel.isPickupElevator(),
                quoteRequestModel.getPickupBuildingType());

        DestinationAddress destinationAddress = new DestinationAddress(quoteRequestModel.getDestinationStreetAddress(),
                quoteRequestModel.getDestinationCity(),
                quoteRequestModel.getDestinationCountry(),
                quoteRequestModel.getDestinationPostalCode(),
                quoteRequestModel.getDestinationNumberOfRooms(),
                quoteRequestModel.isDestinationElevator(),
                quoteRequestModel.getDestinationBuildingType());

        ContactDetails contactDetails = new ContactDetails(quoteRequestModel.getFirstName(),
                quoteRequestModel.getLastName(),
                quoteRequestModel.getEmailAddress(),
                quoteRequestModel.getPhoneNumber());

        quote.setPickupAddress(pickupAddress);
        quote.setDestinationAddress(destinationAddress);
        quote.setContactDetails(contactDetails);
        quote.setInitiationDate(LocalDateTime.now());
        quote.setQuoteStatus(QuoteStatus.PENDING);
        quote.setQuoteIdentifier(new QuoteIdentifier());

        Quote savedQuote = quoteRepository.save(quote);
        return quoteResponseMapper.entityToResponseModel(savedQuote);
    }

    @Override
    public List<QuoteResponseModel> getAllQuotes(QuoteStatus quoteStatus) {
        return quoteResponseMapper.entitiesListToResponseList(quoteRepository.findAllByQuoteStatus(quoteStatus));
    }

    @Override
    public EventResponseModel declineQuote(String quoteId) {
        Quote quote = quoteRepository.findByQuoteIdentifier_QuoteId(quoteId);
        quote.setQuoteStatus(QuoteStatus.DECLINED);
        quoteRepository.save(quote);
        return EventResponseModel.builder()
                .resultType("SUCCESS")
                .event("decline")
                // Dynamically generate the link to the current quote
                .href(WebMvcLinkBuilder
                        .linkTo(QuoteController.class)
                        .slash(quoteId)
                        .withSelfRel()
                        .getHref())
                .build();
    }

    @Override
    public EventResponseModel acceptQuote(String quoteId) {
        // Change quote status to accepted
        // Create a shipment with the quote details
        // Return the shipment id in the event responseModel
        return null;
    }
}
