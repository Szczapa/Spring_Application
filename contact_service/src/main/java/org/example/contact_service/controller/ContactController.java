package org.example.contact_service.controller;

import org.example.contact_service.model.Contact;
import org.example.contact_service.service.ContactService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;

import java.util.UUID;

@Controller
public class ContactController {
    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/")
    public String homePage(){
        return "index";
    }

    @GetMapping("/detail/{contactId}")
    public String detailPage(@PathVariable("contactId") UUID contactId, Model model){
//        contactService.getContact(contactId);
        model.addAttribute("contact", contactService.getContact(contactId));
        return "detail";
    }

    @GetMapping("/list")
    public String ListPage(Model model){
        model.addAttribute("contacts", contactService.getContacts());
        return "list";
    }

    @GetMapping("/search")
    public String searchPage(@RequestAttribute("name") String name, Model model){
        Contact contact = contactService.getContactByName(name);
        if(contact == null){
            return "index";
        }
        model.addAttribute("contact", contact);
        return "detail";
    }
}
