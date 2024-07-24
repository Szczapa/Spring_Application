package org.example.spring_jpa.service;

import org.example.spring_jpa.dao.IPersonRepository;
import org.example.spring_jpa.entity.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final IPersonRepository personRepository;

    public PersonService(IPersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAllPersons(){
        return personRepository.findAll();
    }

    public Person getPersonById(int id){
        return personRepository.findById(id).orElse(null);
    }

    public Person savePerson(Person person){
        return personRepository.save(person);
    }

    public Person updatePerson(Person person){
        return personRepository.save(person);
    }

    public void deletePerson(Person person){
        personRepository.delete(person);
    }

    public Person getPersonByFirstNameAndLastName(String firstName, String lastName){
        return personRepository.findPersonByFirstNameAndLastName(firstName, lastName);
    }
}
