package com.ecom.product_service.events;

import com.ecom.product_service.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductEvent {
    private ProductEventType eventType;
    private ProductDTO product;
}
