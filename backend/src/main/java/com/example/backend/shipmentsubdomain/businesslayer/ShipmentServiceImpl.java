package com.example.backend.shipmentsubdomain.businesslayer;

import com.example.backend.shipmentsubdomain.datalayer.shipment.ShipmentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.StringWriter;


@Service
@AllArgsConstructor
@Slf4j
public class ShipmentServiceImpl implements ShipmentService{
    private final TemplateEngine templateEngine;
    private final ShipmentRepository shipmentRepository;
    @Override
    public String generateShipmentConfirmationEmail(String shipmentId) {
        // Create a Thymeleaf context and add variables
        Context context = new Context();

        context.setVariable("shipmentID", shipmentId != null ? String.valueOf(shipmentId) : "");

        // Process the Thymeleaf template
        StringWriter stringWriter = new StringWriter();
        templateEngine.process("shipmentConfirmation", context, stringWriter);

        log.info("stringWriter: " + stringWriter.toString());
        log.info("shipmentId: " + shipmentId);

        // Get the final HTML content as a string
        return stringWriter.toString();
    }
}
