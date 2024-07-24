package org.example.recette.service;

import org.example.recette.model.Categorie;

import java.util.List;
import java.util.Optional;

public interface ICategorieService {
    List<Categorie> getAllCategories();
    Optional<Categorie> getCategorie(int id);
    void addCategorie(Categorie categorie);
    void updateCategorie(Categorie categorie);
    void deleteCategorie(int id);
}
