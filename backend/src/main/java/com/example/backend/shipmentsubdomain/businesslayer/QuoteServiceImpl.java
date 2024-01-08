package com.example.backend.shipmentsubdomain.businesslayer;

import com.example.backend.shipmentsubdomain.datalayer.*;
import com.example.backend.shipmentsubdomain.datamapperlayer.quote.QuoteRequestMapper;
import com.example.backend.shipmentsubdomain.datamapperlayer.quote.QuoteResponseMapper;
import com.example.backend.shipmentsubdomain.datamapperlayer.shipment.AddressMapper;
import com.example.backend.shipmentsubdomain.datamapperlayer.shipment.QuoteResponseToShipmentMapper;
import com.example.backend.util.EmailUtil;
import com.example.backend.shipmentsubdomain.utils.exceptions.QuoteNotFoundException;
import com.example.backend.shipmentsubdomain.presentationlayer.QuoteController;
import com.example.backend.shipmentsubdomain.presentationlayer.QuoteRequestModel;
import com.example.backend.shipmentsubdomain.presentationlayer.QuoteResponseModel;
import com.example.backend.shipmentsubdomain.presentationlayer.event.EventResponseModel;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService {
    private final QuoteRepository quoteRepository;
    private final QuoteRequestMapper quoteRequestMapper;
    private final QuoteResponseMapper quoteResponseMapper;
    private final QuoteResponseToShipmentMapper quoteResponseToShipmentMapper;
    private final AddressMapper addressMapper;
    private final EmailUtil emailUtil;
    private final TemplateEngine templateEngine;

    @Generated
    String generateDeclineQuoteEmailContentString(Quote quote) {
        try {
            Context context = new Context();
            context.setVariable("quoteId", quote.getQuoteIdentifier() != null ? quote.getQuoteIdentifier().getQuoteId() : "");
            context.setVariable("pickupAddress", quote.getPickupAddress() != null ? quote.getPickupAddress() : "");
            context.setVariable("destinationAddress", quote.getDestinationAddress() != null ? quote.getDestinationAddress() : "");
            return templateEngine.process("declineQuote", context);
        } catch (Exception e) {
            log.error("Error while generating shipment confirmation email", e);
            return "";
        }
    }
    @Override
    public QuoteResponseModel getQuote(String quoteId) {
        Quote existingQuote = quoteRepository.findByQuoteIdentifier_QuoteId(quoteId);
        if (existingQuote == null) {
            throw new QuoteNotFoundException("quoteId not found: " + quoteId);
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
        emailUtil.SslEmail(quote.getContactDetails().getEmailAddress(), "Quote Declined", generateDeclineQuoteEmailContentString(quote));
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
        Quote quote = quoteRepository.findByQuoteIdentifier_QuoteId(quoteId);
        quote.setQuoteStatus(QuoteStatus.ACCEPTED);
        quoteRepository.save(quote);
        return EventResponseModel.builder()
                .resultType("SUCCESS")
                .event("accept")
                .href(WebMvcLinkBuilder
                        .linkTo(QuoteController.class)
                        .slash(quoteId)
                        .withSelfRel()
                        .getHref())
                .build();
    }

    @Override
    public EventResponseModel convertQuoteToShipment(String quoteId) {
        Quote quote = quoteRepository.findByQuoteIdentifier_QuoteId(quoteId);
        quote.setQuoteStatus(QuoteStatus.CREATED);
        quoteRepository.save(quote);

        return EventResponseModel.builder()
                .resultType("SUCCESS")
                .event("convert")
                // Dynamically generate the link to the current quote
                .href(WebMvcLinkBuilder
                        .linkTo(QuoteController.class)
                        .slash(quoteId)
                        .withSelfRel()
                        .getHref())
                .build();
    }
}
