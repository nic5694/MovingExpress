package com.example.backend.shipmentsubdomain.presentationlayer.shipment;

import com.example.backend.shipmentsubdomain.businesslayer.ShipmentService;
import com.example.backend.shipmentsubdomain.presentationlayer.QuoteResponseModel;
import com.example.backend.util.EmailUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(path = "api/v1/movingexpress/shipments")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
public class ShipmentController {
    private final EmailUtil emailUtil;
    private final ShipmentService shipmentService;

    @ResponseStatus(org.springframework.http.HttpStatus.CREATED)
    @PostMapping()
    public ShipmentResponseModel createShipment(@RequestBody QuoteResponseModel quoteResponseModel){
        return shipmentService.createShipment(quoteResponseModel);
    }
    //Email Test Endpoint
    //TODO: Remove later
    @GetMapping("/sendMail/{to}")
    public void sendMail(@PathVariable String to) {
        String subject = "Shipment Creation Confirmation";
        String text = shipmentService.generateShipmentConfirmationEmail("TR67MH6F4K5NMOJK6");
        emailUtil.SslEmail(to, subject, text);
    }
}
