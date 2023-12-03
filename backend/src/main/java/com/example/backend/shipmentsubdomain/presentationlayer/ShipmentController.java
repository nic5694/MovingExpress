package com.example.backend.shipmentsubdomain.presentationlayer;

import com.example.backend.shipmentsubdomain.businesslayer.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/shipments")
@RequiredArgsConstructor
public class ShipmentController {
    private final QuoteService quoteService;

    @PostMapping()
    public QuoteResponse addQuote(@RequestBody QuoteRequest quoteRequest){
        return quoteService.addQuote(quoteRequest);
    }
}
