package com.college.service;

import com.college.model.Person;
import com.college.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> getPersonByPersonType(String personType){
        List<Person> personList = null;
        if(personType != null){
            personList = personRepository.getPersonByPersonType(personType).stream().collect(Collectors.toList());
        }
        return personList;
    }

    public Person findPersonByEmail(String email){
        Person person = null;
        if(email != null){
             person = personRepository.findPersonByEmail(email);
        }
        return person;
    }
    public void savePerson(Person person){
        personRepository.save(person);
    }

    public Person updatePerson(Person person, int id){
        Person existPerson =  null;
        Optional<Person> getPersonById = personRepository.findById(id);
        if(getPersonById.isPresent()) {
            existPerson = getPersonById.get();
        }
        if(existPerson.getFirstName() != null){
            existPerson.setFirstName(person.getFirstName());
        }
        if(existPerson.getLastName() != null){
            existPerson.setLastName(person.getLastName());
        }
        if(existPerson.getMiddleName() != null){
            existPerson.setMiddleName(person.getMiddleName());
        }
        if(existPerson.getGender() != null){
            existPerson.setGender(person.getGender());
        }
        if(existPerson.getProgram() != null){
            existPerson.setProgram(person.getProgram());
        }
        if(existPerson.getAddress() != null){
            existPerson.setAddress(person.getAddress());
        }
        if(existPerson.getEmail() != null){
            existPerson.setEmail(person.getEmail());
        }
        if(existPerson.getDateOfBirth() != null){
            existPerson.setDateOfBirth(person.getDateOfBirth());
        }
        if(existPerson.getPersonType() != null){
            existPerson.setPersonType(person.getPersonType());
        }
        personRepository.save(existPerson);
        return existPerson;
    }

    public List<Person> findPersonByFirstNameAndPersonType(String firstName, String personType){
        List<Person> personList = null;
        if(firstName != null && personType != null) {
            personList = personRepository.findPersonByFirstNameAndPersonType(firstName, personType).stream().collect(Collectors.toList());
        }
        return personList;
    }

    public void deletePerson(int id){
        Person findPersonById= personRepository.findById(id).get();
        if(findPersonById != null)
            personRepository.delete(findPersonById);
    }
}
