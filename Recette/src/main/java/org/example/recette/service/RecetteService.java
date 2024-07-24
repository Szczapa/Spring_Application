package org.example.recette.service;


import org.example.recette.model.Ingredients;
import org.example.recette.model.Recette;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecetteService implements IRecetteService {
    private final Map<Integer, Recette> recettes;

    public RecetteService() {
        recettes = new HashMap<>();
    }


    @Override
    public Optional<Recette> getRecette(int id) {
        return Optional.ofNullable(recettes.get(id));
    }

    public List<Recette> getAllRecettes(String nom) {
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
