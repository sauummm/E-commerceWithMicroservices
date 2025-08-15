package com.ecom.api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator customRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route("product-service", r -> r.path("/api/products/**")
                        .uri("lb://PRODUCT_SERVICE"))
                .route("order-service", r -> r.path("/api/orders/**")
                        .uri("lb://ORDER-SERVICE"))
                .route("user-service", r -> r.path("/api/users/**")
                        .uri("lb://USER-SERVICE"))
                .route("cart-service", r -> r.path("/api/cart/**")
                        .uri("lb://CART-SERVICE"))
                .route("payment-service", r -> r.path("/api/payments/**")
                        .uri("lb://PAYMENT-SERVICE"))
                .build();
    }

}
