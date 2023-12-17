package com.example.backend.customersubdomain.datalayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CustomerRepositoryTest {
    @Autowired
    CustomerRepository customerRepository;
    String userid = "auth0|123456789";
    Customer customer = Customer.builder()
            .userId("auth0|123456789")
            .firstName("John")
            .lastName("Doe")
            .email("test@gmail.com")
            .phoneNumber("1234567890")
            .build();

    @BeforeEach
    public void setup(){
        customerRepository.deleteAll();
        customer = customerRepository.save(customer);
    }
    @Test
    void getCustomerByUserId() {
        Customer customer = customerRepository.findCustomerByUserId(userid);
        assertEquals(userid, customer.getUserId());
    }

    @Test
    void deleteCustomerByUserId() {
        customerRepository.deleteCustomerByUserId(userid);
        Customer customer = customerRepository.findCustomerByUserId(userid);
        assertNull(customer);
    }

    @Test
    void existsByUserId() {
        boolean exists = customerRepository.existsByUserId(userid);
        assertTrue(exists);
    }
}