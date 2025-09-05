package com.ecom.user_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {
    public NewTopic userTopic(){
        return new NewTopic("user-topic", 3, (short) 1);
    }

}
