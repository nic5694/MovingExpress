package com.example.backend.shipmentsubdomain.businesslayer;

import com.example.backend.shipmentsubdomain.presentationlayer.QuoteResponseModel;
import com.example.backend.shipmentsubdomain.presentationlayer.shipment.ShipmentResponseModel;

public interface ShipmentService {
    String generateShipmentConfirmationEmail(String shipmentId);
    ShipmentResponseModel createShipment(QuoteResponseModel quoteResponseModel);
}
