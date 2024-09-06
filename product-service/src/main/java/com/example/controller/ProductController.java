package com.example.controller;

import com.example.model.ProductRequest;
import com.example.response.ApiResponse;
import com.example.response.DeleteResponse;
import com.example.response.ProductResponse;
import com.example.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/product")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping()
    public ResponseEntity<ApiResponse<ProductResponse>> createProduct(@RequestBody ProductRequest productRequest) {
        ApiResponse<ProductResponse> response = ApiResponse.<ProductResponse>builder()
                .message("Successfully save product!")
                .payload(productService.createProduct(productRequest))
                .status(HttpStatus.CREATED)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/get-all-products")
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getAllProducts() {
        ApiResponse<List<ProductResponse>> response = ApiResponse.<List<ProductResponse>>builder()
                .message("Successfully get all products!")
                .payload(productService.getAllProduct())
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> getProductById(@PathVariable("id") Long id) {
        ApiResponse<ProductResponse> response = ApiResponse.<ProductResponse>builder()
                .message("Successfully get product!")
                .payload(productService.getProductById(id))
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> updateProduct(@PathVariable("id") Long id, @RequestBody ProductRequest productRequest) {
        ApiResponse<ProductResponse> response = ApiResponse.<ProductResponse>builder()
                .message("Successfully updated product!")
                .payload(productService.updateProduct(id, productRequest))
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponse> deleteProduct(@PathVariable("id") Long id) {
        if(!productService.deleteProduct(id)){
            DeleteResponse response = DeleteResponse.builder()
                    .message("Product not found. Can't delete product!")
                    .status(HttpStatus.NO_CONTENT)
                    .dateTime(LocalDateTime.now())
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        DeleteResponse response = DeleteResponse.builder()
                .message("Successfully deleted product!")
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
