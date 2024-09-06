package com.example.controller;

import com.example.model.dto.request.CustomerRequest;
import com.example.model.dto.response.ApiResponse;
import com.example.model.dto.response.CustomerResponse;
import com.example.model.dto.response.DeleteResponse;
import com.example.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/create-customer")
    public ResponseEntity<ApiResponse<CustomerResponse>>  createCustomer(@RequestBody CustomerRequest customerRequest) {
        ApiResponse<CustomerResponse> response = ApiResponse.<CustomerResponse>builder()
                .message("Successfully save customer")
                .payload(customerService.createCustomer(customerRequest))
                .status(HttpStatus.CREATED)
                .dateTime((LocalDateTime.now()))
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/get-all-customers")
    public ResponseEntity<ApiResponse<List<CustomerResponse>>> getAllCustomer() {
        ApiResponse<List<CustomerResponse>> response = ApiResponse.<List<CustomerResponse>>builder()
                .message("Successfully get all customers")
                .payload(customerService.getAllCustomer())
                .status(HttpStatus.OK)
                .dateTime((LocalDateTime.now()))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerResponse>> getCustomer(@PathVariable Long id) {
        ApiResponse<CustomerResponse> response = ApiResponse.<CustomerResponse>builder()
                .message("Successfully get a customer")
                .payload(customerService.getCustomerById(id))
                .status(HttpStatus.OK)
                .dateTime((LocalDateTime.now()))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerResponse>> updateCustomer(@PathVariable Long id, @RequestBody CustomerRequest customerRequest) {
        ApiResponse<CustomerResponse> response = ApiResponse.<CustomerResponse>builder()
                .message("Successfully get a customer")
                .payload( customerService.updateCustomer(id, customerRequest))
                .status(HttpStatus.OK)
                .dateTime((LocalDateTime.now()))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponse> deleteCustomer(@PathVariable Long id) {
        if(!customerService.deleteCustomer(id)){
            DeleteResponse response = DeleteResponse.builder()
                    .message("Customer not found. Can't delete customer")
                    .status(HttpStatus.NO_CONTENT)
                    .dateTime(LocalDateTime.now())
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        DeleteResponse response = DeleteResponse.builder()
                .message("Successfully deleted customer.")
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
