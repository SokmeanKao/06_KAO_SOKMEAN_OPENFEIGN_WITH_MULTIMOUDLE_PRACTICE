package com.example.model.dto.response;

import com.example.response.CustomerResponse;
import com.example.response.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderResponse {
    private Long id;
    private CustomerResponse customer;
    private List<ProductResponse> products;
    private LocalDate orderDate;

}
