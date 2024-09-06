package com.example.controller;

import com.example.model.dto.request.OrderRequest;
import com.example.model.dto.response.OrderResponse;
import com.example.response.ApiResponse;
import com.example.response.DeleteResponse;
import com.example.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping()
    public ResponseEntity<ApiResponse<OrderResponse>> createOrder(@RequestBody OrderRequest orderRequest) {
        ApiResponse<OrderResponse> response = ApiResponse.<OrderResponse>builder()
                .message("Successfully save order!")
                .payload(orderService.createOrder(orderRequest))
                .status(HttpStatus.CREATED)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<OrderResponse>>> getAllOrders() {
        ApiResponse<List<OrderResponse>> response = ApiResponse.<List<OrderResponse>>builder()
                .message("Successfully get all orders!")
                .payload(orderService.getAllOrder())
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<ApiResponse<OrderResponse>> updateOrder(@PathVariable("id") Long id, @RequestBody OrderRequest orderRequest) {
        ApiResponse<OrderResponse> response = ApiResponse.<OrderResponse>builder()
                .message("Successfully update order!")
                .payload(orderService.updateOrder(id, orderRequest))
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderResponse>> getOrderById(@PathVariable("id") Long id) {
        ApiResponse<OrderResponse> response = ApiResponse.<OrderResponse>builder()
                .message("Successfully get order!")
                .payload(orderService.getOrderById(id))
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponse> deleteOrderById(@PathVariable("id") Long id) {
        if (!orderService.deleteOrder(id)) {
            DeleteResponse response = DeleteResponse.builder()
                    .message("Order not found. Can't delete order!")
                    .status(HttpStatus.NO_CONTENT)
                    .dateTime(LocalDateTime.now())
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        DeleteResponse response = DeleteResponse.builder()
                .message("Successfully deleted order!")
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
