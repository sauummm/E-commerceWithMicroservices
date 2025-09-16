package com.ecom.cart_service.controller;

import com.ecom.cart_service.entity.Cart;
import com.ecom.cart_service.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long userId){
        return ResponseEntity.ok(cartService.getCartByUserId(userId));
    }

    @PostMapping("/{userId}/add")
    public ResponseEntity<Cart> addItem(
            @PathVariable Long userId,
            @RequestParam Long productId,
            @RequestParam int quantity
    ){
        return ResponseEntity.ok(cartService.addItemtoCart(userId, productId, quantity));
    }

    public ResponseEntity<Cart> removeItem(
            @PathVariable Long userId,
            @RequestParam Long productId
    ){
        return ResponseEntity.ok(cartService.removeItemFromCart(userId, productId));
    }

}
