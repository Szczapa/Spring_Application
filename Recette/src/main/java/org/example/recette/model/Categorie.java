package org.example.recette.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Categorie {
    private int categorieId;
    private String categorieNom;
    private String categorieDescription;
}
