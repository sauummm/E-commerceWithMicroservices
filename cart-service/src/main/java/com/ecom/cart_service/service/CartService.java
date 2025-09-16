package com.ecom.cart_service.service;

import com.ecom.cart_service.entity.Cart;

public interface CartService {
    Cart getCartByUserId(long userId);
    Cart addItemtoCart(Long userId, Long productId, int quantity);
    Cart removeItemFromCart(Long userId, Long productId);
}
