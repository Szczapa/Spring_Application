package org.example.recette.controller;

import jakarta.validation.Valid;
import org.example.recette.model.Categorie;
import org.example.recette.model.Recette;
import org.example.recette.service.ICategorieService;
import org.example.recette.service.IRecetteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RecetteController {

    private final IRecetteService recetteService;
    private final ICategorieService categorieService;

    public RecetteController(IRecetteService recetteService, ICategorieService categorieService) {
        this.recetteService = recetteService;
        this.categorieService = categorieService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/recettes")
    public String recettes(Model model) {
        model.addAttribute("recettes", recetteService.getAllRecettes(""));
        return "recette_template/recettes";
    }

    @GetMapping("/recette/{id}")
    public String recette(@PathVariable("id") int id, Model model) {
        model.addAttribute("recette", recetteService.getRecette(id).orElse(null));
        return "recette_template/recette";
    }

    @GetMapping("/recette/add")
    public String addRecette(Model model) {
        populateRecetteForm(model, new Recette());
        return "recette_template/recetteForm";
    }

    @PostMapping("/recette/add")
    public String submitRecette(@Valid @ModelAttribute("recette") Recette recette, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            populateRecetteForm(model, recette);
            return "recette_template/recetteForm";
        }
        recette.setRecetteCategorie(categorieService.getCategorie(recette.getRecetteCategorie().getCategorieId()).orElse(null));
        recetteService.addRecette(recette);
        return "redirect:/recettes";
    }

    @GetMapping("/recette/edit/{id}")
    public String editRecette(@PathVariable("id") int id, Model model) {
        Recette recette = recetteService.getRecette(id).orElseThrow(() -> new IllegalArgumentException("Invalid recette Id:" + id));
        populateRecetteForm(model, recette);
        return "recette_template/recetteForm";
    }

    @PostMapping("/recette/edit/{id}")
    public String updateRecette(@Valid @ModelAttribute("recette") Recette recette, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            populateRecetteForm(model, recette);
            return "recette_template/recetteForm";
        }
        recette.setRecetteCategorie(categorieService.getCategorie(recette.getRecetteCategorie().getCategorieId()).orElse(null));
        recetteService.updateRecette(recette.getRecetteId(), recette);
        return "redirect:/recettes";
    }

    @GetMapping("/recette/delete/{id}")
    public String deleteRecette(@PathVariable("id") int id) {
        recetteService.deleteRecette(id);
        return "redirect:/recettes";
    }

    private void populateRecetteForm(Model model, Recette recette) {
        model.addAttribute("recette", recette);
        model.addAttribute("ingredients", recetteService.getIngredients());
        model.addAttribute("categories", categorieService.getAllCategories());
    }
}
