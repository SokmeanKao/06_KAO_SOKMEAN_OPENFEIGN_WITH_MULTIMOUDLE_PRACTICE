package com.example.service.serviceimpl;

import com.example.client.CustomerServiceFeignClient;
import com.example.client.ProductServiceFeignClient;
import com.example.model.Order;
import com.example.model.dto.request.OrderRequest;
import com.example.response.ApiResponse;
import com.example.response.CustomerResponse;
import com.example.model.dto.response.OrderResponse;
import com.example.repository.OrderRepository;
import com.example.response.ProductResponse;
import com.example.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerServiceFeignClient customerServiceFeignClient;
    private final ProductServiceFeignClient productServiceFeignClient;

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        // Fetch a customer by customerId for feign client
        CustomerResponse customerResponse = customerServiceFeignClient
                .getCustomerById(orderRequest.getCustomerId())
                .getBody()
                .getPayload();

        // Fetch product using stream and
        // mapping each productId to a ProductResponse
        List<ProductResponse> productResponseList = orderRequest.getProductIds().stream()
                .map(productId -> productServiceFeignClient
                        .getProductById(productId)
                        .getBody()
                        .getPayload())  // Get ProductResponse for each ID
                .collect(Collectors.toList());

        // Return OrderResponse with customer and productList
        return orderRepository.save(orderRequest.toEntity()).toResponse(customerResponse, productResponseList);
    }

    @Override
    public List<OrderResponse> getAllOrder() {
        //Fetch all orders
        List<Order> orders = orderRepository.findAll();

        // Map each Order entity to OrderResponse
        return orders.stream().map(order -> {
            // Fetch customer details from the customer service using FeignClient
            CustomerResponse customerResponse = customerServiceFeignClient.getCustomerById(order.getCustomerId()).getBody().getPayload();

            // Fetch product for each product ID
            List<ProductResponse> productResponses = order.getProductIds().stream()
                    .map(productId -> {
                        ResponseEntity<ApiResponse<ProductResponse>> productResponseEntity = productServiceFeignClient.getProductById(productId);
                        return productResponseEntity.getBody().getPayload();
                    })
                    .collect(Collectors.toList());

            // Convert Order entity to OrderResponse
            return order.toResponse(customerResponse, productResponses);
        }).collect(Collectors.toList());
    }


    @Override
    public OrderResponse getOrderById(Long id) {
        // Fetch the order by ID from the repository, or throw an exception if not found
        Order order = orderRepository.findById(id).orElseThrow();

        // Fetch customer details from customer service
        ResponseEntity<ApiResponse<CustomerResponse>> customerResponseEntity = customerServiceFeignClient.getCustomerById(order.getCustomerId());
        CustomerResponse customerResponse = customerResponseEntity.getBody().getPayload();

        // Fetch product details for each product ID
        List<ProductResponse> productResponses = order.getProductIds().stream()
                .map(productId -> {
                    // Get each product by ID from product service feign client
                    ResponseEntity<ApiResponse<ProductResponse>> productResponseEntity = productServiceFeignClient.getProductById(productId);
                    return productResponseEntity.getBody().getPayload();
                })
                .collect(Collectors.toList());

        // Convert the Order entity to an OrderResponse
        return order.toResponse(customerResponse, productResponses);
    }


    @Override
    public boolean deleteOrder(Long id) {
        if(orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public OrderResponse updateOrder(Long id, OrderRequest orderRequest) {
        // Fetch a customer by customerId for feign client
        CustomerResponse customerResponse = customerServiceFeignClient
                .getCustomerById(orderRequest.getCustomerId())
                .getBody()
                .getPayload();

        // Fetch product using stream and
        // mapping each productId to a ProductResponse
        List<ProductResponse> productResponseList = orderRequest.getProductIds().stream()
                .map(productId -> productServiceFeignClient
                        .getProductById(productId)
                        .getBody()
                        .getPayload())  // Get ProductResponse for each ID
                .collect(Collectors.toList());

        // Return OrderResponse with customer and productList
        return orderRepository.save(orderRequest.toEntity(id)).toResponse(customerResponse, productResponseList);
    }


}
