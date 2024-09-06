package com.example.service.serviceimpl;

import com.example.model.Product;
import com.example.model.dto.request.ProductRequest;
import com.example.model.dto.response.ProductResponse;
import com.example.respository.ProductRepository;
import com.example.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        return productRepository.save(productRequest.toEntity()).toResponse();
    }

    @Override
    public List<ProductResponse> getAllProduct() {
        return productRepository.findAll().stream().map(Product::toResponse).toList();
    }

    @Override
    public ProductResponse getProductById(Long id) {
        return productRepository.findById(id).get().toResponse();
    }

    @Override
    public boolean deleteProduct(Long id) {
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
