package com.ecom.cart_service.service;

import com.ecom.cart_service.entity.Cart;
import com.ecom.cart_service.entity.CartItem;
import com.ecom.cart_service.event.CartEvent;
import com.ecom.cart_service.repository.CartItemRepository;
import com.ecom.cart_service.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    private static final String TOPIC = "cart-events";

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
        Cart savedCart = cartRepository.save(cart);

        CartEvent event = new CartEvent("ITEM_ADDED", userId, productId, quantity);
        kafkaTemplate.send(TOPIC, event);

        return savedCart;
    }

    @Override
    public Cart removeItemFromCart(Long userId, Long productId) {
        Cart cart = getCartByUserId(userId);

        cart.getItems().removeIf(item -> item.getProductId().equals(productId));

        Cart savedCart = cartRepository.save(cart);

        CartEvent event = new CartEvent("ITEM_REMOVED", userId, productId, 0);
        kafkaTemplate.send(TOPIC, event);

        return savedCart;
    }
}
