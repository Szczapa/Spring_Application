package org.example.meuble.service;

import org.example.meuble.dao.ICartItemRepository;
import org.example.meuble.entity.CartItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    public final ICartItemRepository cartItemRepository;

    public CartService(ICartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    public CartItem GetCartItemById(int id) {
        return cartItemRepository.findById(id)
                .orElse(null);
    }

    public void addToCart(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }

    public void removeFromCart(CartItem cartItem) {
        cartItemRepository.delete(cartItem);
    }

    public void clearCart() {
        cartItemRepository.deleteAll();
    }

    public List<CartItem> getCartItemByItemId(int id) {
        return cartItemRepository.findByFurniture_Id(id) ;

    }

    public boolean isFurnitureInCart(int furnitureId) {
        List<CartItem> cartItems = getCartItemByItemId(furnitureId);
        return !cartItems.isEmpty();
    }
}
