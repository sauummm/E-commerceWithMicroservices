package com.ecom.product_service.consumer;

import com.ecom.product_service.events.ProductEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderEventsConsumer {
    @KafkaListener(topics = "order-topic", groupId = "product-service-group")
    public void consume(ProductEvent event){
        System.out.println("Consumed event in Product Service: " +event);
    }


}
