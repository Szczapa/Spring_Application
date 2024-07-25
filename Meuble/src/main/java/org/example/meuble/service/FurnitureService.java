package org.example.meuble.service;

import org.example.meuble.dao.IFurnitureRepository;
import org.example.meuble.entity.Furniture;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FurnitureService {

    public final IFurnitureRepository furnitureRepository;

    public FurnitureService(IFurnitureRepository furnitureRepository) {
        this.furnitureRepository = furnitureRepository;
    }

    public List<Furniture> getAllFurniture() {
        return furnitureRepository.findAll();
    }

    public void SaveFurniture(Furniture furniture) {
        furnitureRepository.save(furniture);
    }

    public Furniture GetFurnitureById(int id) {
        return furnitureRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid furniture Id:" + id));
    }

    public void DeleteFurniture(Furniture furniture) {
        furnitureRepository.delete(furniture);
    }
}
