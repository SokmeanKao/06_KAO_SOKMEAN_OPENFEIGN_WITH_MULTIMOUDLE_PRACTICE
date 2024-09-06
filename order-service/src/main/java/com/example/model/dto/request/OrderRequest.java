package com.example.model.dto.request;

import com.example.model.Order;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private Long customerId;
    private List<Long> productIds;
    private LocalDate orderDate;

    public Order toEntity() {
        return new Order(
                null,
                this.customerId,
                this.productIds,
                this.orderDate
        );
    }

    public Order toEntity(Long id) {
        return new Order(
                id,
                this.customerId,
                this.productIds,
                this.orderDate
        );
    }
}
