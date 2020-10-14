package com.college.controller;

import com.college.model.Person;
import com.college.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public List<Person> getPersonList(String personType){
        List<Person> personList = personService.getPersonByPersonType(personType);
        return personList;
    }

    @RequestMapping(value = "/person", method = RequestMethod.POST)
    public ResponseEntity savePerson(@RequestBody Person person){
        Person personByEmail = personService.findPersonByEmail(person.getEmail());
        if(personByEmail != null){
            return ResponseEntity.status(400).body("Email id already exist. Please enter a different email id.");
        }
        personService.savePerson(person);
        return ResponseEntity.status(201).body("User created successfully");
    }

    @RequestMapping(value = "/person/{firstName}", method = RequestMethod.GET)
    public List<Person> getPersonByName(@PathVariable String firstName, String personType){
        List<Person> personList = personService.findPersonByFirstNameAndPersonType(firstName, personType);
        return personList;
    }

    @RequestMapping(value = "/person/{personId}", method = RequestMethod.PUT)
    public Person updatePerson(@RequestBody Person person, @PathVariable int personId){
        Person existPerson = personService.updatePerson(person, personId);
        return existPerson;
    }

    @RequestMapping(value = "/person/{personId}", method = RequestMethod.DELETE)
    public void deletePerson(@PathVariable int personId){
        personService.deletePerson(personId);
    }
}
