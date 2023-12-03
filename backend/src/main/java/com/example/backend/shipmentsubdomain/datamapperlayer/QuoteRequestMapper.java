package com.example.backend.shipmentsubdomain.datamapperlayer;
import com.example.backend.shipmentsubdomain.datalayer.Quote;
import com.example.backend.shipmentsubdomain.datalayer.QuoteIdentifier;
import com.example.backend.shipmentsubdomain.presentationlayer.QuoteRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel="spring")
public interface QuoteRequestMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "quoteIdentifier", ignore = true),
            @Mapping(target = "pickupAddress", ignore = true),
            @Mapping(target = "destinationAddress", ignore = true),
            @Mapping(target = "contactDetails", ignore = true)
    })
    Quote requestModelToEntity(QuoteRequest quoteRequest);
}
