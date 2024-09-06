package com.example.controller;

import com.example.model.dto.request.ProductRequest;
import com.example.model.dto.response.ProductResponse;
import com.example.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping()
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest) {
        return productService.createProduct(productRequest);
    }

    @GetMapping("/get-all-products")
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProduct();
    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        if(!productService.deleteProduct(id)){
            return "Delete product fail";
        }
        return "Delete product successfully";
    }
}
