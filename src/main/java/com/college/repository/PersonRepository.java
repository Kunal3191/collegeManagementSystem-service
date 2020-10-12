package com.college.repository;

import com.college.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    List<Person> getPersonByPersonType(String personType);
    Person findPersonByEmail(String email);
    List<Person> findPersonByFirstNameAndPersonType(String firstName, String pesontype);
}
