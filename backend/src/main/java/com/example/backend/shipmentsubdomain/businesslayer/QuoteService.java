package com.example.backend.shipmentsubdomain.businesslayer;

import com.example.backend.shipmentsubdomain.presentationlayer.QuoteRequestModel;
import com.example.backend.shipmentsubdomain.presentationlayer.QuoteResponseModel;
import com.example.backend.shipmentsubdomain.datalayer.QuoteStatus;
import com.example.backend.shipmentsubdomain.presentationlayer.event.EventResponseModel;

import java.util.List;

public interface QuoteService {
    List<QuoteResponseModel> getAllQuotes(QuoteStatus quoteStatus);

    QuoteResponseModel getQuote(String quoteId);

    QuoteResponseModel addQuote(QuoteRequestModel quoteRequest);

    EventResponseModel declineQuote(String quoteId);
    EventResponseModel acceptQuote(String quoteId);
    EventResponseModel convertQuoteToShipment(String quoteId);
}
