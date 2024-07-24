package org.example.spring_jpa.controller;

import jakarta.validation.Valid;
import org.example.spring_jpa.entity.Person;

import org.example.spring_jpa.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/")
    public String getAllPersons(Model model) {
        model.addAttribute("persons", personService.getAllPersons());
        return "home";
    }

    @GetMapping("/addForm")
    public String addForm(Model model) {
        model.addAttribute("person", new Person());
        return "add-person";
    }

    @PostMapping("/addPerson")
    public String addPerson( @Valid  @ModelAttribute("person") Person person, BindingResult result) {
        if ( result.hasErrors() ) {
            return "add-person";
        }
        personService.savePerson(person);
        return "redirect:/";
    }

}