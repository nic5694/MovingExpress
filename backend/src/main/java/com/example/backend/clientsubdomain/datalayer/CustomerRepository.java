package com.example.backend.clientsubdomain.datalayer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    Customer findCustomerByUserId(String userId);
    void deleteCustomerByUserId(String userId);
    boolean existsByUserId(String userId);
}
