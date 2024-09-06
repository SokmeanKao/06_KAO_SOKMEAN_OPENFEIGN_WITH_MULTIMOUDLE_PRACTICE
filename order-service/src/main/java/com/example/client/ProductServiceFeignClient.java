package com.example.client;

import com.example.response.ApiResponse;
import com.example.response.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "product-service",url= "http://localhost:8087/api/v1/product")
public interface ProductServiceFeignClient {
    @GetMapping("/get-all-products")
    ResponseEntity<ApiResponse<List<ProductResponse>>> getAllProduct();

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<ProductResponse>> getProductById(@PathVariable("id") Long id);
}
