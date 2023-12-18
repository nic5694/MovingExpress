package com.example.backend.shipmentsubdomain.presentationlayer;

import com.example.backend.shipmentsubdomain.businesslayer.QuoteService;
import com.example.backend.shipmentsubdomain.datalayer.QuoteStatus;
import com.example.backend.shipmentsubdomain.presentationlayer.event.EventRequestModel;
import com.example.backend.shipmentsubdomain.presentationlayer.event.EventResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "api/v1/movingexpress/quotes")
@CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
@RequiredArgsConstructor
public class QuoteController {
    private final QuoteService quoteService;
    @GetMapping()
    public ResponseEntity<List<QuoteResponseModel>> getAllQuotes(@RequestParam QuoteStatus quoteStatus){
        return ResponseEntity.ok().body(quoteService.getAllQuotes(quoteStatus));
    }

    @GetMapping("/retrieve")
    public ResponseEntity<QuoteResponseModel> getQuote(@RequestParam String quoteId){
        return ResponseEntity.ok().body(quoteService.getQuote(quoteId));
    }

    @PostMapping(value = "/request")
    public ResponseEntity<QuoteResponseModel> addQuote(@RequestBody QuoteRequestModel quoteRequestModel){
        return ResponseEntity.status(HttpStatus.CREATED).body(quoteService.addQuote(quoteRequestModel));
    }

    @ResponseStatus(org.springframework.http.HttpStatus.CREATED)
    @PostMapping(value = "/{quoteId}/events")
    public EventResponseModel createQuoteEvent(@RequestBody EventRequestModel eventRequestModel,
                                               @PathVariable String quoteId){

        switch (eventRequestModel.getEvent()){
            case "decline":
                return quoteService.declineQuote(quoteId);
//            case "accept":
//                return quoteService.acceptQuote(quoteId);
            default:
                throw new IllegalArgumentException("Unexpected event value: " + eventRequestModel.getEvent());
        }
    }

}
