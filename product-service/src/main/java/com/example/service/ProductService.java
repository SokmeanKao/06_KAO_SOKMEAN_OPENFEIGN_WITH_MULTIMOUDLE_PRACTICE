package com.example.service;

import com.example.model.ProductRequest;
import com.example.response.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);

    List<ProductResponse> getAllProduct();

    ProductResponse getProductById(Long id);

    boolean deleteProduct(Long id);

    ProductResponse updateProduct(Long id, ProductRequest productRequest);
}
