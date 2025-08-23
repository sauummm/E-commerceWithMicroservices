package com.ecom.product_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic productEventsTopic(){
        return new NewTopic("product-events", 3, (short) 1);
    }

}
