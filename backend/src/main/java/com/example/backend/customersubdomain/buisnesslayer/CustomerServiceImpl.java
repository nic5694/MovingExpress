package com.example.backend.customersubdomain.buisnesslayer;

import com.example.backend.customersubdomain.datalayer.Customer;
import com.example.backend.customersubdomain.datalayer.CustomerRepository;
import com.example.backend.customersubdomain.datamapperlayer.CustomerRequestMapper;
import com.example.backend.customersubdomain.datamapperlayer.CustomerResponseMapper;
import com.example.backend.customersubdomain.presentationlayer.CustomerRequestModel;
import com.example.backend.customersubdomain.presentationlayer.CustomerResponseModel;
import com.example.backend.shipmentsubdomain.datalayer.shipment.ShipmentRepository;
import com.example.backend.util.exceptions.CustomerNotFoundException;
import com.example.backend.util.exceptions.InvalidRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository customerRepository;
    private final CustomerResponseMapper customerResponseMapper;
    private final CustomerRequestMapper customerRequestMapper;
    private final ShipmentRepository shipmentRepository;
    @Override
    public CustomerResponseModel getCustomerByUserId(String userId) {
        Customer customer = customerRepository.findCustomerByUserId(userId);
        if (customer == null)
            throw new CustomerNotFoundException("Customer with userId: " + userId + " could not be found.");

        return customerResponseMapper.toCustomerResponse(customer);
    }
    @Override
    public CustomerResponseModel addCustomer(CustomerRequestModel customerRequest) {
        if (customerRepository.existsByUserId(customerRequest.getUserId()))
            throw new InvalidRequestException("Customer with userId: " + customerRequest.getUserId() + " already exists.");
        Customer customer = customerRequestMapper.toCustomer(customerRequest);
        customer.setUserId(customerRequest.getUserId());
        customerRepository.save(customer);
        return customerResponseMapper.toCustomerResponse(customer);
    }

    @Override
    public CustomerResponseModel updateCustomer(CustomerRequestModel customerRequest, String userId) {
        Customer customer = customerRepository.findCustomerByUserId(userId);
        if (customer == null)
            throw new CustomerNotFoundException("Customer with userId" + userId + " was not found.");

        customer.setFirstName(customerRequest.getFirstName() != null ? customerRequest.getFirstName() : customer.getFirstName());
        customer.setLastName(customerRequest.getLastName() != null ? customerRequest.getLastName() : customer.getLastName());
        customer.setEmail(customerRequest.getEmail() != null ? customerRequest.getEmail() : customer.getEmail());
        customer.setPhoneNumber(customerRequest.getPhoneNumber() != null ? customerRequest.getPhoneNumber() : customer.getPhoneNumber());
        customer.setStreetAddress(customerRequest.getStreetAddress() != null ? customerRequest.getStreetAddress() : customer.getStreetAddress());
        customer.setPostalCode(customerRequest.getPostalCode() != null ? customerRequest.getPostalCode() : customer.getPostalCode());
        customer.setCity(customerRequest.getCity() != null ? customerRequest.getCity() : customer.getCity());
        customer.setCountry(customerRequest.getCountry() != null ? customerRequest.getCountry() : customer.getCountry());
        customer.setProfilePictureUrl(customerRequest.getProfilePictureUrl() != null ? customerRequest.getProfilePictureUrl() : customer.getProfilePictureUrl());

        customerRepository.save(customer);

        return customerResponseMapper.toCustomerResponse(customer);
    }

//    @Override
//    @Transactional
//    public void deleteCustomer(String userId) {
//        if (customerRepository.existsByUserId(userId))
//            throw new InvalidRequestException("Customer does not exist, could not be deleted.");
//        shipmentRepository.deleteAll(shipmentRepository.findShipmentByUserId(userId));
////        shipmentRepository.findShipmentByUserId(userId).forEach(shipment -> {
////            if(shipment.getStatus().equals(Status.TRANSIT) || shipment.getStatus().equals(Status.LOADING) || shipment.getStatus().equals(Status.QUOTED)){
////                throw new InvalidRequestException("Customer has pending shipments, could not be deleted.");
////            } else if (shipment.getStatus().equals(Status.DELIVERED)) {
////                shipmentRepository.delete(shipment);
////            }
////        });
//        customerRepository.deleteCustomerByUserId(userId);
//        if (!customerRepository.existsByUserId(userId))
//            throw new InvalidRequestException("Customer could not be deleted.");
//
//    }
    @Override
    public boolean checkIfCustomerExists(String userId) {
        return customerRepository.existsByUserId(userId);
    }
}

