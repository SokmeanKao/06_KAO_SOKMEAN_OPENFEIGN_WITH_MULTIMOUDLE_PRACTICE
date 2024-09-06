package com.example.model;

import com.example.response.CustomerResponse;
import com.example.model.dto.response.OrderResponse;
import com.example.response.ProductResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "orders")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    @ElementCollection
    private List<Long> productIds;
    private LocalDate orderDate;

    public OrderResponse toResponse(CustomerResponse customerResponse, List<ProductResponse> productResponses) {
        return new OrderResponse(
                this.id,
                customerResponse,
                productResponses,
                this.orderDate
        );
    }
}
