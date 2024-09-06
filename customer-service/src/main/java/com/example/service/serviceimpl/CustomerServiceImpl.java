package com.example.service.serviceimpl;

import com.example.model.Customer;
import com.example.model.dto.request.CustomerRequest;
import com.example.repository.CustomerRepository;
import com.example.response.CustomerResponse;
import com.example.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        return customerRepository.save(customerRequest.toEntity()).toResponse();
    }

    @Override
    public List<CustomerResponse> getAllCustomer() {
        return customerRepository.findAll().stream().map(Customer::toResponse).toList();
    }

    @Override
    public CustomerResponse getCustomerById(Long id) {
        return customerRepository.findById(id).get().toResponse();
    }

    @Override
    public CustomerResponse updateCustomer(Long id, CustomerRequest customerRequest) {
        return customerRepository.save(customerRequest.toEntity()).toResponse();
    }

    @Override
    public boolean deleteCustomer(Long id) {
        if(customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
