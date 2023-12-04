package com.example.backend.shipmentsubdomain.presentationlayer;

import com.example.backend.shipmentsubdomain.businesslayer.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/movingexpress/quotes")
@RequiredArgsConstructor
public class QuoteController {
    private final QuoteService quoteService;

    @PostMapping("/request")
    public QuoteResponse addQuote(@RequestBody QuoteRequest quoteRequest){
        return quoteService.addQuote(quoteRequest);
    }
}
