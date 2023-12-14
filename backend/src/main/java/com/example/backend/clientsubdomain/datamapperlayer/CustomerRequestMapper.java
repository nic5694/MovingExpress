package com.example.backend.clientsubdomain.datamapperlayer;

import com.example.backend.clientsubdomain.datalayer.Customer;
import com.example.backend.clientsubdomain.presentationlayer.CustomerRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerRequestMapper {
    @Mapping(ignore = true, target = "userId")
    @Mapping(ignore = true, target = "id")
    Customer toCustomer(CustomerRequestModel customerRequest);
}
