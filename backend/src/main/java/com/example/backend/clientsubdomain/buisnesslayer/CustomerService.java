package com.example.backend.clientsubdomain.buisnesslayer;

import com.example.backend.clientsubdomain.presentationlayer.CustomerRequestModel;
import com.example.backend.clientsubdomain.presentationlayer.CustomerResponseModel;

public interface CustomerService {
    CustomerResponseModel getCustomerByUserId(String userId);
    CustomerResponseModel createCustomer(CustomerRequestModel customerRequestModel, String userId);
    CustomerResponseModel updateCustomer(CustomerRequestModel customerRequestModel, String userId);
    void deleteCustomer(String userId);
    boolean checkIfCustomerExists(String userId);
}
