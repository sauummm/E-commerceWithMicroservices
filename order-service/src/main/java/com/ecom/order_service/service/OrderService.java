package com.ecom.order_service.service;

import com.ecom.order_service.dto.OrderCreatedEvent;
import com.ecom.order_service.entity.Order;
import com.ecom.order_service.producer.OrderProducer;
import com.ecom.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderProducer orderProducer;

    @Transactional
    public Order placeOrder(Order order){
        order.setStatus("CREATED");
        Order savedOrder = orderRepository.save(order);

        OrderCreatedEvent event = new OrderCreatedEvent(
        savedOrder.getId(),
        savedOrder.getProductId(),
        savedOrder.getQuality()
        );
        orderProducer.sendOrderEvent(event);

        return savedOrder;
    }
}
