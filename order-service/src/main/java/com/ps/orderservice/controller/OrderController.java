package com.ps.orderservice.controller;

import com.ps.orderservice.service.OrderService;
import com.ps.orderservice.model.UserDto;
import com.ps.orderservice.entity.Order;
import com.ps.orderservice.exception.OrderServiceException;
import com.ps.orderservice.feign.UserClient;
import com.ps.orderservice.repository.OrderRepository;
import com.ps.orderservice.response.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;
    private final UserClient userClient;
    private final OrderService orderService;

    @PostMapping("/create")
    public Order createOrder(@RequestBody Order order) {
        return orderRepository.save(order);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }


    @GetMapping("/{id}")
    public OrderResponse getOrderById(@PathVariable Long id) {
        Order order = orderRepository.findById(id).orElseThrow(()-> new OrderServiceException(HttpStatus.NOT_FOUND.toString(), "id: " + id + "not found"));

        // Gọi user-service bằng Feign
        UserDto user = userClient.getUserById(order.getUserId());

        return new OrderResponse(order.getId(), order.getProduct(), order.getPrice(), user);
    }


    @PostMapping
    public Order placeOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }
}