package com.ecommerce.customer.controller;

import com.ecommerce.customer.entity.Customer;
import com.ecommerce.customer.service.CustomerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

private final CustomerService customerService;

  public CustomerController(CustomerService customerService){
      this.customerService = customerService;
  }

  @PostMapping
  public Customer createCustomer(@RequestBody Customer customer){
     return customerService.createCustomer(customer);
  }

}
