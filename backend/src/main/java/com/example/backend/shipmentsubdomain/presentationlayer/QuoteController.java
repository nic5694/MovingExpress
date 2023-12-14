package com.example.backend.shipmentsubdomain.presentationlayer;

import com.example.backend.shipmentsubdomain.businesslayer.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "api/v1/movingexpress/quotes")
@CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
@RequiredArgsConstructor
public class QuoteController {
    private final QuoteService quoteService;

    @GetMapping("/retrieve/{quoteId}")
    public QuoteResponse getQuote(@PathVariable String quoteId){
        return quoteService.getQuote(quoteId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/request")
    public QuoteResponse addQuote(@RequestBody QuoteRequest quoteRequest){
        return quoteService.addQuote(quoteRequest);
    }
}
