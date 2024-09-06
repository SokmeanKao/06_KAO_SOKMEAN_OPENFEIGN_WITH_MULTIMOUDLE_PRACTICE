package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRequest {
    private String name;
    private double price;

    public Product toEntity() {
        return new Product(
                null,
                this.name,
                this.price
        );
    }
    public Product toEntity(Long id) {
        return new Product(
                id,
                this.name,
                this.price
        );
    }
}
