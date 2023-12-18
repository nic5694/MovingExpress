package com.example.backend.shipmentsubdomain.datamapperlayer.shipment;

import com.example.backend.shipmentsubdomain.datalayer.shipment.Shipment;
import com.example.backend.shipmentsubdomain.presentationlayer.QuoteRequestModel;
import lombok.Generated;
import org.mapstruct.Mapper;

@Generated
@Mapper(componentModel="spring")
public interface ShipmentRequestMapper {

    Shipment quoteRequestToShipmentMapper(QuoteRequestModel quoteRequestModel);
}
