package com.ecom.cart_service.service;

import com.ecom.cart_service.entity.Cart;
import com.ecom.cart_service.entity.CartItem;
import com.ecom.cart_service.repository.CartItemRepository;
import com.ecom.cart_service.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public Cart getCartByUserId(long userId) {
        return cartRepository.findByUserId(userId)
                .orElseGet(() ->{
                    Cart newCart = Cart.builder()
                            .userId(userId)
                            .build();
                    return cartRepository.save(newCart);
                });
    }

    @Override
    public Cart addItemtoCart(Long userId, Long productId, int quantity) {
        Cart cart = getCartByUserId(userId);
        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst();

        if(existingItem.isPresent()){
            existingItem.get().setQuantity(existingItem.get().getQuantity() + quantity);
        }
        else {
            CartItem newItem = CartItem.builder()
                    .productId(productId)
                    .cart(cart)
                    .quantity(quantity)
                    .build();
            cart.getItems().add(newItem);
        }
        return cartRepository.save(cart);
    }

    @Override
    public Cart removeItemFromCart(Long userId, Long productId) {
        Cart cart = getCartByUserId(userId);

        cart.getItems().removeIf(item -> item.getProductId().equals(productId));
        return cartRepository.save(cart);
    }
}
