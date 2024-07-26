package org.example.meuble.service;

import org.example.meuble.dao.ICartItemRepository;
import org.example.meuble.entity.CartItem;
import org.example.meuble.entity.Furniture;
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

    public CartItem addToCart(Furniture furniture) {

        CartItem cardExist = cartItemRepository.findByFurniture_Id(furniture.getId());


        if (cardExist != null) {
            cardExist.setQuantity(cardExist.getQuantity() + 1);
            cartItemRepository.save(cardExist);
            return cardExist;
        }
        CartItem cartItem = CartItem.builder()
                .furniture(furniture)
                .quantity(1)
                .build();
        cartItemRepository.save(cartItem);

        return  cartItem;
    }


    public void removeFromCart(CartItem cartItem) {
        cartItemRepository.delete(cartItem);
    }

    public void clearCart() {
        cartItemRepository.deleteAll();
    }

    public CartItem getCartItemByItemId(int id) {
        return cartItemRepository.findByFurniture_Id(id);

    }

    public boolean isFurnitureInCart(int furnitureId) {
        CartItem cartItems = getCartItemByItemId(furnitureId);
        return cartItems != null;
    }
}
