package org.example.meuble.controller;

import jakarta.validation.Valid;
import org.example.meuble.entity.CartItem;
import org.example.meuble.entity.Furniture;
import org.example.meuble.service.CartService;
import org.example.meuble.service.FurnitureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartItemController {
    public final CartService cartService;
    private final FurnitureService furnitureService;

    public CartItemController(CartService cartService, FurnitureService furnitureService) {
        this.cartService = cartService;
        this.furnitureService = furnitureService;
    }

    @GetMapping("/cartItems")
    public String getCartItems(Model model) {
        System.out.println(cartService.getAllCartItems());
        model.addAttribute("cartItems", cartService.getAllCartItems());
//        model.addAttribute("cartItems", cartService.getAllCartItems());
        return "cart/cartItems";
    }

//    @PostMapping("/cartItem/add")
//    public String addCartItem(@RequestParam int furnitureId, Model model) {
//        Furniture furniture = furnitureService.GetFurnitureById(furnitureId);
//        if (furniture.getStock() <= 0) {
//            model.addAttribute("error", "Stock insuffisant pour ajouter cet article au panier.");
//            return "redirect:/furnitures"; // Or another appropriate view
//        }
//        CartItem cartItem = CartItem.builder()
//                .furniture(furniture)
//                .quantity(1) // Default quantity, can be modified as needed
//                .build();
//        furniture.setStock(furniture.getStock() - 1);
//        furnitureService.SaveFurniture(furniture);
//        cartService.addToCart(cartItem);
//        return "redirect:/cartItems";
//    }    @PostMapping("/cartItem/add")

    @PostMapping("/cartItem/add")
    public String addCartItem(@RequestParam int furnitureId, Model model) {
        Furniture furniture = furnitureService.GetFurnitureById(furnitureId);
        if (furniture.getStock() <= 0) {
            model.addAttribute("error", "Stock insuffisant pour ajouter cet article au panier.");
            return "redirect:/furnitures"; // Or another appropriate view
        }

        if (cartService.addToCart(furniture) != null) {
            furniture.setStock(furniture.getStock() - 1);
            furnitureService.SaveFurniture(furniture);
            return "redirect:/cartItems";
        }

        return "redirect:/furnitures";
    }

    @GetMapping("/cartItem/remove")
    public String removeCartItem(@RequestParam int id) {
        Furniture furniture = cartService.GetCartItemById(id).getFurniture();
        cartService.removeFromCart(cartService.GetCartItemById(id));
        System.out.println(furniture);
        furniture.setStock(furniture.getStock() + 1);
        furnitureService.SaveFurniture(furniture);
        return "redirect:/cartItems";
    }

    @GetMapping("/cartItems/clear")
    public String clearCart() {
        cartService.clearCart();
        return "redirect:/cartItems";
    }
}
