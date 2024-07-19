package org.example.productsystem.service;

import org.example.productsystem.model.Product;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final Map<UUID, Product> products;

    public ProductService() {
        products = new HashMap<>();
        Product product = Product.builder()
                .productId(UUID.randomUUID())
                .name("Product 1")
                .category("Category_1")
                .price(100.0f)
                .build();

        Product product2 = Product.builder()
                .productId(UUID.randomUUID())
                .name("Product 2")
                .category("Category_2")
                .price(200.0f)
                .build();

        Product product3 = Product.builder()
                .productId(UUID.randomUUID())
                .name("Product 3")
                .category("Category_3")
                .price(300.0f)
                .build();

        products.put(product.getProductId(), product);
        products.put(product2.getProductId(), product2);
        products.put(product3.getProductId(), product3);
    }

    public List<Product> getProducts() {
        return List.copyOf(products.values());
    }

    public List<Product> getProducts(Float price, String category) {
        if (price == -1.0 && category.isEmpty()) {
            return List.copyOf(products.values());
        }

        return products.values().stream()
                .filter(p -> (price == -1.0 || p.getPrice() <= price) && (category.isEmpty() || p.getCategory().equals(category)))
                .collect(Collectors.toList());
    }

    public Product getProduct(UUID productId) {
        System.out.println("Searching for product with ID: " + productId);

        System.out.println(List.copyOf(products.values()));

        Product foundProduct = products.values().stream()
                .filter(p -> p.getProductId().equals(productId))
                .findFirst()
                .orElse(null);

        if (foundProduct == null) {
            System.out.println("Product not found for ID: " + productId);
        } else {
            System.out.println("Found product: " + foundProduct.getName());
        }

        return foundProduct;
    }
}
