package org.example.meuble.dao;

import org.example.meuble.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICartItemRepository extends JpaRepository<CartItem, Integer> {
    CartItem findByFurniture_Id(int id);
}
