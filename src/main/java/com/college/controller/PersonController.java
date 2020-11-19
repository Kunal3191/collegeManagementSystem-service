package com.college.controller;

import com.college.model.Person;
import com.college.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public List<Person> getPersonList(@RequestParam String personType){
        List<Person> personList = personService.getPersonByPersonType(personType);
        return personList;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/person", method = RequestMethod.POST)
    public Map<String, String> savePerson(@RequestBody Person person){
        Map<String, String> hashMap = new HashMap<>();
        Person personByEmail = personService.findPersonByEmail(person.getEmail());
        if(personByEmail != null){
            hashMap.put("message", "Email id already exist. Please enter a different email id.");
            return hashMap;
        }
        personService.savePerson(person);
        hashMap.put("message", "user created successfully");
        return hashMap;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/person/{firstName}", method = RequestMethod.GET)
    public List<Person> getPersonByName(@PathVariable String firstName, String personType){
        List<Person> personList = personService.findPersonByFirstNameAndPersonType(firstName, personType);
        return personList;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/person/{personId}", method = RequestMethod.PUT)
    public Person updatePerson(@RequestBody Person person, @PathVariable int personId){
        Person existPerson = personService.updatePerson(person, personId);
        return existPerson;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/person/{personId}", method = RequestMethod.DELETE)
    public Map<String, String> deletePerson(@PathVariable int personId){
        Map<String, String> hashMap = new HashMap<>();
        if(personId > 0) {
            personService.deletePerson(personId);
            hashMap.put("message", "Records deleted successfully");
            return hashMap;
        }
        else{
            hashMap.put("message", "Record deletion failed");
            return hashMap;
        }
    }
}
