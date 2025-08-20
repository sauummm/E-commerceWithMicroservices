package com.ecom.order_service.producer;

import com.ecom.order_service.config.KafkaProducerConfig;
import com.ecom.order_service.dto.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProducer {

    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    public void sendOrderEvent(OrderCreatedEvent event){
        kafkaTemplate.send("order-topic", event);
    }
}
