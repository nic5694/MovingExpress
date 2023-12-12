package com.example.backend.shipmentsubdomain.presentationlayer.shipment;

import com.example.backend.mailerservice.MailService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(path = "api/v1/movingexpress/shipments/")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
public class ShipmentController {
    private final MailService shipmentService;

    @GetMapping("sendMail/{to}/{subject}/{text}")
    public void sendMail(@PathVariable String to, @PathVariable String subject, @PathVariable String text) throws jakarta.mail.MessagingException {
        shipmentService.sendMail(to, subject, text);
    }
}
