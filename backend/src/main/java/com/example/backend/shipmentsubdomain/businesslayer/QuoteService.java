package com.example.backend.shipmentsubdomain.businesslayer;

import com.example.backend.shipmentsubdomain.presentationlayer.QuoteRequest;
import com.example.backend.shipmentsubdomain.presentationlayer.QuoteResponse;

public interface QuoteService {
    QuoteResponse addQuote(QuoteRequest quoteRequest);
}
