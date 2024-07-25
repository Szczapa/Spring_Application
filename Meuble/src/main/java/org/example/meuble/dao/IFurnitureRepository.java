package org.example.meuble.dao;

import org.example.meuble.entity.Furniture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFurnitureRepository extends JpaRepository<Furniture, Integer> {

}
