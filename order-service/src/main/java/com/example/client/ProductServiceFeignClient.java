package com.example.client;

import com.example.model.dto.response.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "product-service",url= "http://localhost:8087/api/v1/product")
public interface ProductServiceFeignClient {
    @GetMapping("/get-all-products")
    List<ProductResponse> getAllProduct();

    @GetMapping("/{id}")
    ProductResponse getProductById(@PathVariable("id") Long id);
}
