package com.ecom.cart_service.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartEvent {
    private String eventType;
    private Long userId;
    private Long productId;
    private int quantity;

}
