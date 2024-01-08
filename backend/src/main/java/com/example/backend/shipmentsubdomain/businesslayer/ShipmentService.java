package com.example.backend.shipmentsubdomain.businesslayer;

import com.example.backend.shipmentsubdomain.presentationlayer.QuoteResponseModel;
import com.example.backend.shipmentsubdomain.presentationlayer.shipment.ShipmentResponseModel;

import java.util.List;
import java.util.Optional;

public interface ShipmentService {
    ShipmentResponseModel createShipment(QuoteResponseModel quoteResponseModel);
    List<ShipmentResponseModel> getAllShipments(Optional<String> userId, Optional<String> email);
    ShipmentResponseModel getShipment(String shipmentId);

}
