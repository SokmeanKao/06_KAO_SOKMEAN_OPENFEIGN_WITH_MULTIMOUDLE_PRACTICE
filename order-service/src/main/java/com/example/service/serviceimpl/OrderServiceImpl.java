package com.example.service.serviceimpl;

import com.example.client.CustomerServiceFeignClient;
import com.example.client.ProductServiceFeignClient;
import com.example.model.Order;
import com.example.model.dto.request.OrderRequest;
import com.example.model.dto.response.ApiResponse;
import com.example.model.dto.response.CustomerResponse;
import com.example.model.dto.response.OrderResponse;
import com.example.model.dto.response.ProductResponse;
import com.example.repository.OrderRepository;
import com.example.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        ResponseEntity<ApiResponse<CustomerResponse>> customerResponseEntity = customerServiceFeignClient.getCustomerById(orderRequest.getCustomerId());
        CustomerResponse customerResponse = customerResponseEntity.getBody().getPayload();

        List<ProductResponse> productResponses = new ArrayList<>();

        for(Long productId : orderRequest.getProductIds()) {
            ProductResponse productResponse = productServiceFeignClient.getProductById(productId);
            productResponses.add(productResponse);
        }
        Order savedOrder = orderRepository.save(orderRequest.toEntity());
        return savedOrder.toResponse(customerResponse, productResponses);
    }

    @Override
    public List<OrderResponse> getAllOrder() {
        List<Order> orders = orderRepository.findAll();

        List<OrderResponse> orderResponses = orders.stream().map(order -> {
            CustomerResponse customerResponse = customerServiceFeignClient.getCustomerById(order.getCustomerId()).getBody().getPayload();
            List<ProductResponse> productResponses = order.getProductIds().stream()
                    .map(productServiceFeignClient::getProductById)
                    .collect(Collectors.toList());
            return order.toResponse(customerResponse, productResponses);
        }).collect(Collectors.toList());
        return orderResponses;
    }

    @Override
    public OrderResponse getOrderBtId(Long id) {
        Order order = orderRepository.findById(id).get();
        CustomerResponse customerResponse = customerServiceFeignClient.getCustomerById(order.getCustomerId()).getBody().getPayload();
        List<ProductResponse> productResponses = new ArrayList<>(order.getProductIds().stream().map(productServiceFeignClient::getProductById).collect(Collectors.toList()));
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
        ResponseEntity<ApiResponse<CustomerResponse>> customerResponseEntity = customerServiceFeignClient.getCustomerById(orderRequest.getCustomerId());
        CustomerResponse customerResponse = customerResponseEntity.getBody().getPayload();

        List<ProductResponse> productResponses = new ArrayList<>();

        for(Long productId : orderRequest.getProductIds()) {
            ProductResponse productResponse = productServiceFeignClient.getProductById(productId);
            productResponses.add(productResponse);
        }
        Order savedOrder = orderRepository.save(orderRequest.toEntity(id));
        return savedOrder.toResponse(customerResponse, productResponses);
    }


}
