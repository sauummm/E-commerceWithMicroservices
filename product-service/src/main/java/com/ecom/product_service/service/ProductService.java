package com.ecom.product_service.service;

import com.ecom.product_service.dto.ProductDTO;
import com.ecom.product_service.entity.Product;
import com.ecom.product_service.mapper.ProductMapper;
import com.ecom.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public List<ProductDTO> getAllProducts(){
        return repository.findAll()
                .stream()
                .map(ProductMapper::toDto)
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id){
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return ProductMapper.toDto(product);
    }

    public  ProductDTO saveProduct(ProductDTO dto){
        Product product = ProductMapper.toEntity(dto);
        return ProductMapper.toDto(repository.save(product));
    }

    public void deleteProduct(Long id){
        repository.deleteById(id);
    }

}
