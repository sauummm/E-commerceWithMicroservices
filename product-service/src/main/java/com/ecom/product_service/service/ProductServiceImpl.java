package com.ecom.product_service.service;

import com.ecom.product_service.dto.ProductDTO;
import com.ecom.product_service.entity.Product;
import com.ecom.product_service.events.ProductEvent;
import com.ecom.product_service.events.ProductEventType;
import com.ecom.product_service.mapper.ProductMapper;
import com.ecom.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository repository;
    private final ProductEventPublisher eventPublisher;

    @Override
    public List<ProductDTO> getAllProducts(){
        return repository.findAll()
                .stream()
                .map(ProductMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Long id){
        return repository.findById(id).map(ProductMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public  ProductDTO createProduct(ProductDTO dto){
        Product product = repository.save(ProductMapper.toEntity(dto));
        ProductDTO saved = ProductMapper.toDto(product);

        eventPublisher.publish(new ProductEvent(ProductEventType.CREATED, saved));
        return saved;
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO dto) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());

        Product updated = repository.save(product);
        ProductDTO saved = ProductMapper.toDto(updated);

        eventPublisher.publish(new ProductEvent(ProductEventType.UPDATED, saved));
        return saved;
    }

    @Override
    public void deleteProduct(Long id){
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        repository.delete(product);
        eventPublisher.publish(new ProductEvent(ProductEventType.DELETED, ProductMapper.toDto(product)));
    }

}
