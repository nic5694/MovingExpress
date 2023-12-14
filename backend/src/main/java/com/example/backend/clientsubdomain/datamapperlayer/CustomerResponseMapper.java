package com.example.backend.clientsubdomain.datamapperlayer;

import com.example.backend.clientsubdomain.datalayer.Customer;
import com.example.backend.clientsubdomain.presentationlayer.CustomerResponseModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerResponseMapper {
    CustomerResponseModel toCustomerResponse(Customer customer);
}
