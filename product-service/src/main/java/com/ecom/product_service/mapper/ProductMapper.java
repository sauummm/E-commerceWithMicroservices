package com.ecom.product_service.mapper;

import com.ecom.product_service.dto.ProductDTO;
import com.ecom.product_service.entity.Product;

public class ProductMapper {

    public static ProductDTO toDto(Product product){
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity()
        );
    }

    public static Product toEntity(ProductDTO dto){
        return Product.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .build();
    }
}
