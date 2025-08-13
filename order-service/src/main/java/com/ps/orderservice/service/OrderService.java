package com.ps.orderservice.service;

import com.ps.orderservice.entity.Order;
import com.ps.orderservice.event.OrderPlacedEvent;
import com.ps.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public Order createOrder(Order order) {
        Order saved= orderRepository.save(order);

        OrderPlacedEvent event = OrderPlacedEvent.builder()
                .orderId(saved.getId())
                .userId(saved.getUserId())
                .total(saved.getTotal())
                .build();

        kafkaTemplate.send("order-topic", event);
        System.out.println("📤 Đã gửi Kafka event: " + event);

        return saved;
    }
}
