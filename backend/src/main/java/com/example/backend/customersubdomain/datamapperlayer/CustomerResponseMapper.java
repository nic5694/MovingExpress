package com.example.backend.customersubdomain.datamapperlayer;

import com.example.backend.customersubdomain.datalayer.Customer;
import com.example.backend.customersubdomain.presentationlayer.CustomerResponseModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerResponseMapper {
    CustomerResponseModel toCustomerResponse(Customer customer);
}
