package com.ecommerce.customer.service;

import com.ecommerce.customer.entity.Customer;
import com.ecommerce.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer){

        if(customerRepository.existsByEmail(customer.getEmail())){
            throw new RuntimeException("Email already exists");
        }
        return customerRepository.save(customer);
    }
}
