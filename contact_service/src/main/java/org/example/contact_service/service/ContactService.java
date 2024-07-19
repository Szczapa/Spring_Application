package org.example.contact_service.service;

import org.example.contact_service.model.Contact;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ContactService {

    private final Map<UUID, Contact> contacts;

    public ContactService() {
        contacts = new HashMap<>();
        Contact contact = Contact.builder()
                .id(UUID.randomUUID())
                .name("John Doe")
                .phone("123-456-7890")
                .age(30)
                .build();

        Contact contact2 = Contact.builder()
                .id(UUID.randomUUID())
                .name("Jane Doe")
                .phone("123-456-7890")
                .age(25)
                .build();

        Contact contact3 = Contact.builder()
                .id(UUID.randomUUID())
                .name("John Smith")
                .phone("123-456-7890")
                .age(35)
                .build();

        contacts.put(contact.getId(), contact);
        contacts.put(contact2.getId(), contact2);
        contacts.put(contact3.getId(), contact3);
    }

    public List<Contact> getContacts() {
        return List.copyOf(contacts.values());
    }

    public Contact getContact(UUID id) {
        return contacts.get(id);
    }

    public Contact getContactByName(String name) {
        return contacts.values().stream()
                .filter(contact -> contact.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
