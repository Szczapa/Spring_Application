package org.example.spring_jpa.dao;

import org.example.spring_jpa.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPersonRepository extends JpaRepository<Person, Integer> {

    Person findPersonByFirstName(String firstName);

    Person findPersonByFirstNameAndLastName(String firstName, String lastName);
}
