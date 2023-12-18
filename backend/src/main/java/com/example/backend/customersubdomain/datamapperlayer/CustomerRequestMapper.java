package com.example.backend.customersubdomain.datamapperlayer;

import com.example.backend.customersubdomain.datalayer.Customer;
import com.example.backend.customersubdomain.presentationlayer.CustomerRequestModel;
import lombok.Generated;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Generated
@Mapper(componentModel = "spring")
public interface CustomerRequestMapper {
    @Mapping(ignore = true, target = "userId")
    @Mapping(ignore = true, target = "id")
    Customer toCustomer(CustomerRequestModel customerRequest);
}
