package com.example.controller;

import com.example.model.dto.request.OrderRequest;
import com.example.model.dto.response.OrderResponse;
import com.example.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping()
    public OrderResponse createOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.createOrder(orderRequest);
    }

    @GetMapping()
    public List<OrderResponse> getAllOrders() {
        return orderService.getAllOrder();
    }

    @PutMapping ("/{id}")
    public OrderResponse updateOrder(@PathVariable("id") Long id, @RequestBody OrderRequest orderRequest) {
        return orderService.updateOrder(id, orderRequest);
    }

    @GetMapping("/{id}")
    public OrderResponse getOrderById(@PathVariable("id") Long id) {
        return orderService.getOrderBtId(id);
    }

    @DeleteMapping("/{id}")
    public String deleteOrderById(@PathVariable("id") Long id) {
        if(!orderService.deleteOrder(id)){
            return "Can't delete this order";
        }
        return "Delete successful";
    }

}
