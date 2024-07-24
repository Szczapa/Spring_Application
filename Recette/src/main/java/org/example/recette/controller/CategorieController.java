package org.example.recette.controller;

import org.example.recette.model.Categorie;
import org.example.recette.service.CategorieService;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategorieController {

    private final CategorieService categorieService;

    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    @GetMapping("/categories")
    public String categories(Model model) {
        model.addAttribute("categories", categorieService.getCategories());
        return "categorie_template/categories";
    }

    @GetMapping("/categorie/{id}")
    public String categorie(@PathVariable("id") int categorieId, Model model) {
        model.addAttribute("categorie", categorieService.getCategorie(categorieId));
        return "categorie_template/categorie";
    }

    @GetMapping("/categorie/add")
    public String addCategorie(Model model) {
        model.addAttribute("categorie", new Categorie());
        return "categorie_template/categorieForm";
    }

    @PostMapping("/categorie/add")
    public String submitCategorie(@Valid @ModelAttribute("categorie")  Categorie categorie, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "categorie_template/categorieForm";
        }
        categorieService.addCategorie(categorie);
        return "redirect:/categories";
    }

    @GetMapping("/categorie/edit/{id}")
    public String editCategorie(@PathVariable("id") int categorieId, Model model) {
        model.addAttribute("categorie", categorieService.getCategorie(categorieId));
        return "categorie_template/categorieForm";
    }

    @PostMapping("/categorie/edit/{id}")
    public String updateCategorie(@Valid @ModelAttribute("categorie")  Categorie categorie, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "categorie_template/categorieForm";
        }
        categorieService.updateCategorie(categorie);
        return "redirect:/categories";
    }

    @GetMapping("/categorie/delete/{id}")
    public String deleteCategorie(@PathVariable("id") int categorieId) {
        categorieService.deleteCategorie(categorieId);
        return "redirect:/categories";
    }
}
