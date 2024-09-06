package com.example.service;

import com.example.model.dto.request.CustomerRequest;
import com.example.model.dto.response.CustomerResponse;

import java.util.List;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest customerRequest);

    List<CustomerResponse> getAllCustomer();

    CustomerResponse getCustomerById(Long id);

    CustomerResponse updateCustomer(Long id, CustomerRequest customerRequest);

    boolean deleteCustomer(Long id);
}
