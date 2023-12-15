package com.example.backend.shipmentsubdomain.businesslayer;

import com.example.backend.shipmentsubdomain.presentationlayer.QuoteRequestModel;
import com.example.backend.shipmentsubdomain.presentationlayer.QuoteResponseModel;

public interface QuoteService {
    QuoteResponseModel addQuote(QuoteRequestModel quoteRequestModel);
}
