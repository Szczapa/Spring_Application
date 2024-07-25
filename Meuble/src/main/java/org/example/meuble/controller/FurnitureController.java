package org.example.meuble.controller;

import jakarta.validation.Valid;
import org.example.meuble.entity.Furniture;
import org.example.meuble.service.CartService;
import org.example.meuble.service.FurnitureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FurnitureController {

    public final FurnitureService furnitureService;
    private final CartService cartService;


    public FurnitureController(FurnitureService furnitureService, CartService cartService) {
        this.furnitureService = furnitureService;
        this.cartService = cartService;
    }

    @GetMapping("/furnitures")
    public String getAllFurniture(Model model) {
        model.addAttribute("furnitures", furnitureService.getAllFurniture());
        return "furniture/furnitures";
    }

    @GetMapping("/furniture/add")
    public String addFurniture(Model model) {
        model.addAttribute("furniture", new Furniture());
        return "furniture/furniture-Form";
    }

    @PostMapping("/furniture/add")
    public String addFurniture(@Valid @ModelAttribute("furniture") Furniture furniture, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "furniture/furniture-Form";
        }
        if (furniture.getStock() < 0) {
            bindingResult.rejectValue("stock", "error.furniture", "Stock must be positive");
            return "furniture/furniture-Form";
        }

        furnitureService.SaveFurniture(furniture);
        return "redirect:/furnitures";
    }

    @PostMapping("/furniture/restock")
    public String restockFurniture(@RequestParam int furnitureId) {
        furnitureService.GetFurnitureById(furnitureId).setStock(furnitureService.GetFurnitureById(furnitureId).getStock() + 10);
        furnitureService.SaveFurniture(furnitureService.GetFurnitureById(furnitureId));
        return "redirect:/furnitures";
    }

    @GetMapping("/furniture/remove")
    public String removeFurniture(@RequestParam int id, Model model) {

        if (cartService.isFurnitureInCart(id)) {
            model.addAttribute("furnitures", furnitureService.getAllFurniture());
            model.addAttribute("error", "Furniture is in cart");
            System.out.println("Furniture is in cart");
            return "furniture/furnitures";
        }
        furnitureService.DeleteFurniture(furnitureService.GetFurnitureById(id));
        return "redirect:/furnitures";
    }


}
