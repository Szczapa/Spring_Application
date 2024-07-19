package org.example.productsystem.controller;

import org.example.productsystem.model.Product;
import org.example.productsystem.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Controller
public class ProductController {

    private final ProductService productService;

    private ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("products", productService.getProducts());
        return "products";
    }

    @GetMapping("/products-filter")
    public String productFilter(@RequestParam(value = "price",required = false, defaultValue = "-1.0") Float price,
                                @RequestParam(value = "category", required = false, defaultValue = "")String category,
                                Model model){

        List<Product> products = productService.getProducts(price, category);
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/product/{productId}")
    public String product(@PathVariable(value = "productId") UUID productId, Model model) {
        System.out.println(productId);
        Product product = productService.getProduct(productId);
        System.out.println(product);
        model.addAttribute("product", product);
        return "product";
    }
}
