package org.example.recette.service;

import org.example.recette.model.Ingredients;
import org.example.recette.model.Recette;

import java.util.List;
import java.util.Optional;

public interface IRecetteService {
    Optional<Recette> getRecette(int id);
    List<Recette> getAllRecettes(String nom);
    void addRecette(Recette recette);
    void updateRecette(int id, Recette recette);
    void deleteRecette(int id);
    List<Ingredients> getIngredients();
}
