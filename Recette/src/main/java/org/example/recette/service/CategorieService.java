package org.example.recette.service;

import org.example.recette.model.Categorie;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CategorieService implements ICategorieService {
    private final Map<Integer, Categorie> categories;

    public CategorieService() {
        categories = new HashMap<>();

        Categorie categorie = Categorie.builder()
                .categorieId(1)
                .categorieNom("Entrée")
                .categorieDescription("Une entrée")
                .build();
        categories.put(categorie.getCategorieId(), categorie);

        Categorie categorie2 = Categorie.builder()
                .categorieId(2)
                .categorieNom("Plat")
                .categorieDescription("Un plat")
                .build();

        categories.put(categorie2.getCategorieId(), categorie2);

        Categorie categorie3 = Categorie.builder()
                .categorieId(3)
                .categorieNom("Dessert")
                .categorieDescription("Un dessert")
                .build();

        categories.put(categorie3.getCategorieId(), categorie3);
    }


    public List<Categorie> getAllCategories() {
        return List.copyOf(categories.values());
    }


    public Optional<Categorie> getCategorie(int id) {
        return Optional.ofNullable(categories.get(id));

    }


    public void addCategorie(Categorie categorie) {
        categorie.setCategorieId(categories.size() + 1);
        categories.put(categorie.getCategorieId(), categorie);
    }


    public void updateCategorie(Categorie categorie) {
        categories.put(categorie.getCategorieId(), categorie);
    }


    public void deleteCategorie(int id) {
        categories.remove(id);
    }
}
