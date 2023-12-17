package com.example.backend.shipmentsubdomain.businesslayer;

import com.example.backend.shipmentsubdomain.datalayer.*;
import com.example.backend.shipmentsubdomain.datamapperlayer.quote.QuoteRequestMapper;
import com.example.backend.shipmentsubdomain.datamapperlayer.quote.QuoteResponseMapper;
import com.example.backend.shipmentsubdomain.exceptions.NotFoundException;
import com.example.backend.shipmentsubdomain.presentationlayer.QuoteRequestModel;
import com.example.backend.shipmentsubdomain.presentationlayer.QuoteResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService{
    private final QuoteRepository quoteRepository;
    private final QuoteRequestMapper quoteRequestMapper;
    private final QuoteResponseMapper quoteResponseMapper;

    @Override
    public QuoteResponseModel getQuote(String quoteId) {
        Quote existingQuote=quoteRepository.findByQuoteIdentifier_QuoteId(quoteId);
        if(existingQuote==null){
            throw new NotFoundException("quoteId not found: "+quoteId);
        }

        return quoteResponseMapper.entityToResponseModel(existingQuote);
    }

    @Override
    public QuoteResponseModel addQuote(QuoteRequestModel quoteRequestModel) {
        Quote quote=quoteRequestMapper.requestModelToEntity(quoteRequestModel);

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

        Quote savedQuote=quoteRepository.save(quote);
        return quoteResponseMapper.entityToResponseModel(savedQuote);
    }

    @Override
    public List<QuoteResponseModel> getAllQuotes(QuoteStatus quoteStatus) {
        return quoteResponseMapper.entitiesListToResponseList(quoteRepository.findAllByQuoteStatus(quoteStatus));
    }
}
