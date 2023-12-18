package com.example.backend.customersubdomain.buisnesslayer;

import com.example.backend.customersubdomain.presentationlayer.CustomerRequestModel;
import com.example.backend.customersubdomain.presentationlayer.CustomerResponseModel;

public interface CustomerService {
    CustomerResponseModel getCustomerByUserId(String userId);
    CustomerResponseModel addCustomer(CustomerRequestModel customerRequestModel);
    CustomerResponseModel updateCustomer(CustomerRequestModel customerRequestModel, String userId);
//    void deleteCustomer(String userId);
    boolean checkIfCustomerExists(String userId);
}
