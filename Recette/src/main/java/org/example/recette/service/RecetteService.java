package org.example.recette.service;


import org.example.recette.model.Ingredients;
import org.example.recette.model.Recette;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RecetteService {
    private final Map<Integer, Recette> recettes;

    public RecetteService() {
        recettes = new HashMap<>();
    }

    public Recette getRecette(int id) {
        return recettes.values().stream()
                .filter(r -> r.getRecetteId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Recette> getRecettes(String nom) {
        if (nom.isEmpty()) {
            return List.copyOf(recettes.values());
        }
        return recettes.values().stream()
                .filter(r -> r.getRecetteNom().toLowerCase().contains(nom.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void addRecette(Recette recette) {
        recette.setRecetteId(recettes.size() + 1);
        recettes.put(recette.getRecetteId(), recette);
    }

    public void updateRecette(int id, Recette recette) {
        recettes.put(id, recette);
    }

    public void deleteRecette(int id) {
        recettes.remove(id);
    }

    public List<Ingredients> getIngredients() {
        return Arrays.asList(Ingredients.values());
    }

}
