package com.example.backend.shipmentsubdomain.presentationlayer;

import com.example.backend.shipmentsubdomain.businesslayer.QuoteService;
import com.example.backend.shipmentsubdomain.datalayer.Quote;
import com.example.backend.shipmentsubdomain.datalayer.QuoteStatus;
import com.example.backend.shipmentsubdomain.presentationlayer.event.EventRequestModel;
import com.example.backend.shipmentsubdomain.presentationlayer.event.EventResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "api/v1/movingexpress/quotes")
@CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
@RequiredArgsConstructor
public class QuoteController {
    private final QuoteService quoteService;
    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<QuoteResponseModel> getAllQuotes(@RequestParam QuoteStatus quoteStatus){
        return  quoteService.getAllQuotes(quoteStatus);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/retrieve")
    public QuoteResponseModel getQuote(@RequestParam String quoteId){
        return quoteService.getQuote(quoteId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/request")
    public QuoteResponseModel addQuote(@RequestBody QuoteRequestModel quoteRequestModel){
        return quoteService.addQuote(quoteRequestModel);
    }

    @PostMapping(value = "/{quoteId}/events")
    public EventResponseModel createQuoteEvent(@RequestBody EventRequestModel eventRequestModel,
                                               @PathVariable String quoteId){

        switch (eventRequestModel.getEvent()){
            case "decline":
                return quoteService.declineQuote(quoteId);
//            case "accept":
//                return quoteService.acceptQuote(quoteId);
            default:
                throw new IllegalStateException("Unexpected value: " + eventRequestModel.getEvent());
        }
    }

}
