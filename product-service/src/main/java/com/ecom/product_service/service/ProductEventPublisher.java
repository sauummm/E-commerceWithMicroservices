package com.ecom.product_service.service;

import com.ecom.product_service.events.ProductEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductEventPublisher {

    private final KafkaTemplate<String, ProductEvent> kafkaTemplate;

    public void publish(ProductEvent event){
        kafkaTemplate.send("product-events", event);
    }

}
