package com.example.service;

import com.example.model.Order;
import com.example.model.dto.request.OrderRequest;
import com.example.model.dto.response.OrderResponse;

import java.util.List;

public interface OrderService {

    OrderResponse createOrder(OrderRequest orderRequest);

    List<OrderResponse> getAllOrder();

    OrderResponse updateOrder(Long id, OrderRequest orderRequest);

    OrderResponse getOrderBtId(Long id);

    boolean deleteOrder(Long id);
}
