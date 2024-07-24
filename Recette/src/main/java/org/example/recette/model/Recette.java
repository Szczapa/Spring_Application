package org.example.recette.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Recette {
    private int recetteId;

    @NotBlank(message = "Nom de la recette ne peut pas être vide")
    @NotNull(message = "Nom de la recette ne peut pas être nul")
    @Size(min = 2, max = 50, message = "Nom de la recette doit être entre 2 et 50 caractères")
    private String recetteNom;

   @NotEmpty(message = "Les ingrédients de la recette ne peut pas être vide")
    private List<Ingredients> recetteIngredients;


    @NotNull(message = "La catégorie de la recette ne peut pas être nul")
    private Categorie recetteCategorie;

    @NotBlank(message = "Les instructions de la recette ne peut pas être vide")
    @NotNull(message = "Les instructions de la recette ne peut pas être nul")
    private String instructions;

}
