package com.example.backend.clientsubdomain.buisnesslayer;

import com.example.backend.clientsubdomain.datalayer.Customer;
import com.example.backend.clientsubdomain.datalayer.CustomerRepository;
import com.example.backend.clientsubdomain.datamapperlayer.CustomerRequestMapper;
import com.example.backend.clientsubdomain.datamapperlayer.CustomerResponseMapper;
import com.example.backend.clientsubdomain.presentationlayer.CustomerRequestModel;
import com.example.backend.clientsubdomain.presentationlayer.CustomerResponseModel;
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
    @Override
    public CustomerResponseModel getCustomerByUserId(String userId) {
        Customer customer = customerRepository.findCustomerByUserId(userId);
        if (customer == null)
            throw new CustomerNotFoundException("Customer with userId: " + userId + " could not be found.");

        return customerResponseMapper.toCustomerResponse(customer);
    }
    @Override
    public CustomerResponseModel createCustomer(CustomerRequestModel customerRequest, String userId) {
        if (customerRepository.existsByUserId(userId))
            throw new InvalidRequestException("Customer with userId: " + userId + " already exists.");
        Customer customer = customerRequestMapper.toCustomer(customerRequest);
        customer.setUserId(userId);
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
        customer.setPhone(customerRequest.getPhone() != null ? customerRequest.getPhone() : customer.getPhone());
        customer.setAddress(customerRequest.getAddress() != null ? customerRequest.getAddress() : customer.getAddress());
        customer.setPostalCode(customerRequest.getPostalCode() != null ? customerRequest.getPostalCode() : customer.getPostalCode());
        customer.setCity(customerRequest.getCity() != null ? customerRequest.getCity() : customer.getCity());
        customer.setCountry(customerRequest.getCountry() != null ? customerRequest.getCountry() : customer.getCountry());

        customerRepository.save(customer);

        return customerResponseMapper.toCustomerResponse(customer);
    }

    @Override
    public void deleteCustomer(String userId) {
        customerRepository.deleteCustomerByUserId(userId);

        if (customerRepository.existsByUserId(userId))
            throw new InvalidRequestException("Customer does not exist, could not be deleted.");

    }
    @Override
    public boolean checkIfCustomerExists(String userId) {
        return customerRepository.existsByUserId(userId);
    }
}

