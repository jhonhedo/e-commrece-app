package com.ecommerce.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateCustomerRequest(
        @NotBlank(message = "Name is required")
        String name, @NotBlank(message = "Email is required")
        @Email(message = "Invalid Email format") String email) {

}
