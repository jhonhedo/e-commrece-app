package com.ecommerce.customer.service;

import com.ecommerce.customer.dto.CreateCustomerRequest;
import com.ecommerce.customer.dto.CustomerResponse;
import com.ecommerce.customer.entity.Customer;
import com.ecommerce.customer.exception.EmailAlreadyExistsException;
import com.ecommerce.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerResponse createCustomer(CreateCustomerRequest request) {

        if (customerRepository.existsByEmail(request.email())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
        Customer customer = new Customer();
        customer.setEmail(request.email());
        customer.setName(request.name());

        Customer savedCustomer = customerRepository.save(customer);
        return new CustomerResponse(savedCustomer.getId(), savedCustomer.getName(), savedCustomer.getEmail());
    }
}
