package com.example.backend.customersubdomain.datamapperlayer;

import com.example.backend.customersubdomain.datalayer.Customer;
import com.example.backend.customersubdomain.presentationlayer.CustomerRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerRequestMapper {
    @Mapping(ignore = true, target = "userId")
    @Mapping(ignore = true, target = "id")
    Customer toCustomer(CustomerRequestModel customerRequest);
}
