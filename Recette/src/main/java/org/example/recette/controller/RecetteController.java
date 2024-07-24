package org.example.recette.controller;

import jakarta.validation.Valid;
import org.example.recette.model.Categorie;
import org.example.recette.model.Recette;
import org.example.recette.service.CategorieService;
import org.example.recette.service.RecetteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class RecetteController {

    private final RecetteService recetteService;
    private final CategorieService categorieService;

    public RecetteController(RecetteService recetteService, CategorieService categorieService) {
        this.recetteService = recetteService;
        this.categorieService = categorieService;
    }


    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/recettes")
    public String recettes(Model model) {
        model.addAttribute("recettes", recetteService.getRecettes(""));
        return "recette_template/recettes";
    }

    @GetMapping("/recette/{id}")
    public String recette(@PathVariable("id") int id, Model model) {
        model.addAttribute("recette", recetteService.getRecette(id));
        return "recette_template/recette";
    }





    @GetMapping("/recette/add")
    public String addRecette(Model model) {
        model.addAttribute("recette", new Recette());
        model.addAttribute("ingredients", recetteService.getIngredients());
        List<Categorie> categories = categorieService.getCategories();
        model.addAttribute("categories", categories);
        return "recette_template/recetteForm";
    }

    @PostMapping("/recette/add")
    public String submitRecette(@Valid @ModelAttribute("recette") Recette recette, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredients", recetteService.getIngredients());
            List<Categorie> categories = categorieService.getCategories();
            model.addAttribute("categories", categories);
            return "recette_template/recetteForm";
        }
        recette.setRecetteCategorie(categorieService.getCategorie(recette.getRecetteCategorie().getCategorieId()));
        recetteService.addRecette(recette);
        return "redirect:/recettes";
    }

    @GetMapping("/recette/edit/{id}")
    public String editRecette(@PathVariable("id") int id, Model model) {
        model.addAttribute("recette", recetteService.getRecette(id));
        model.addAttribute("ingredients", recetteService.getIngredients());
        List<Categorie> categories = categorieService.getCategories();
        model.addAttribute("categories", categories);
        return "recette_template/recetteForm";
    }

    @PostMapping("/recette/edit/{id}")
    public String updateRecette(@Valid @ModelAttribute("recette") Recette recette, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredients", recetteService.getIngredients());
            List<Categorie> categories = categorieService.getCategories();
            model.addAttribute("categories", categories);
            return "recette_template/recetteForm";
        }
        recette.setRecetteCategorie(categorieService.getCategorie(recette.getRecetteCategorie().getCategorieId()));
        recetteService.updateRecette(recette.getRecetteId(), recette);
        return "redirect:/recettes";
    }

    @GetMapping("/recette/delete/{id}")
    public String deleteRecette(@PathVariable("id") int id) {
        recetteService.deleteRecette(id);
        return "redirect:/recettes";
    }
}
