package com.ecommerce.customer.controller;

import com.ecommerce.customer.dto.CreateCustomerRequest;
import com.ecommerce.customer.dto.CustomerResponse;
import com.ecommerce.customer.entity.Customer;
import com.ecommerce.customer.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

private final CustomerService customerService;

  public CustomerController(CustomerService customerService){
      this.customerService = customerService;
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public CustomerResponse createCustomer(@Valid @RequestBody CreateCustomerRequest request){
     return customerService.createCustomer(request);
  }

}
