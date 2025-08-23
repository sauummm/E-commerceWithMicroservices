package com.ecom.product_service.controller;

import com.ecom.product_service.dto.ProductDTO;
import com.ecom.product_service.service.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceImpl productService;



    @GetMapping
    public List<ProductDTO> getAllProducts(){
        return productService.getAllProducts();
    }

    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }
    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO dto){
        return ResponseEntity.ok(productService.createProduct(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO dto){
        return ResponseEntity.ok(productService.updateProduct(id, dto));
    }


    @DeleteMapping
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
