package com.college.service;

import com.college.model.Person;
import com.college.repository.PersonRepository;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    private Person person;

    @BeforeEach
    public void setUp(){
        person = new Person();
        person.setPersonId(1);
        person.setPersonType("student");
        person.setAddressLine1("AAA");
        person.setAddressLine2("BBB");
        person.setCountry("USA");
        person.setEmail("AAA@gmail.com");
        person.setFirstName("CCC");
        person.setLastName("DDD");
        person.setGender("Male");
    }

    @Test
    public void test_getPersonByPersonType_return_personList() throws Exception {
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        when(personRepository.getPersonByPersonType(anyString())).thenReturn(Arrays.asList(person));
        List<Person> persons = personService.getPersonByPersonType(person.getPersonType());
        JSONAssert.assertEquals("CCC", persons.get(0).getFirstName(), false);
        JSONAssert.assertEquals("student", persons.get(0).getPersonType(), false);
        JSONAssert.assertEquals("AAA", persons.get(0).getAddressLine1(), false);
        JSONAssert.assertEquals("BBB", persons.get(0).getAddressLine2(), false);
        JSONAssert.assertEquals("USA", persons.get(0).getCountry(), false);
        JSONAssert.assertEquals("AAA@gmail.com", persons.get(0).getEmail(), false);
        JSONAssert.assertEquals("DDD", persons.get(0).getLastName(), false);
        JSONAssert.assertEquals("Male", persons.get(0).getGender(), false);
        verify(personRepository).getPersonByPersonType(anyString());
        verify(personRepository).getPersonByPersonType(captor.capture());
        JSONAssert.assertEquals("student", captor.getValue(), false);
    }

    @Test
    public void test_findPersonByEmail_return_person() throws Exception {
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        when(personRepository.findPersonByEmail(anyString())).thenReturn(person);
        Person personByEmail = personService.findPersonByEmail(person.getEmail());
        JSONAssert.assertEquals("CCC", personByEmail.getFirstName(), false);
        JSONAssert.assertEquals("student", personByEmail.getPersonType(), false);
        JSONAssert.assertEquals("AAA", personByEmail.getAddressLine1(), false);
        JSONAssert.assertEquals("BBB", personByEmail.getAddressLine2(), false);
        JSONAssert.assertEquals("USA", personByEmail.getCountry(), false);
        JSONAssert.assertEquals("AAA@gmail.com", personByEmail.getEmail(), false);
        JSONAssert.assertEquals("DDD", personByEmail.getLastName(), false);
        JSONAssert.assertEquals("Male", personByEmail.getGender(), false);
        verify(personRepository).findPersonByEmail(anyString());
        verify(personRepository).findPersonByEmail(captor.capture());
        JSONAssert.assertEquals("AAA@gmail.com", captor.getValue(), false);
    }

    @Test
    public void test_savePerson_return_void() throws Exception {
        ArgumentCaptor<Person> captor = ArgumentCaptor.forClass(Person.class);
        personService.savePerson(person);
        verify(personRepository).save(person);
        verify(personRepository).save(captor.capture());
        JSONAssert.assertEquals("CCC", captor.getValue().getFirstName(), false);
        JSONAssert.assertEquals("student", captor.getValue().getPersonType(), false);
        JSONAssert.assertEquals("AAA", captor.getValue().getAddressLine1(), false);
        JSONAssert.assertEquals("BBB", captor.getValue().getAddressLine2(), false);
        JSONAssert.assertEquals("USA", captor.getValue().getCountry(), false);
        JSONAssert.assertEquals("AAA@gmail.com", captor.getValue().getEmail(), false);
        JSONAssert.assertEquals("DDD", captor.getValue().getLastName(), false);
        JSONAssert.assertEquals("Male", captor.getValue().getGender(), false);
    }

    @Test
    public void test_updatePerson_return_person() throws Exception {
        when(personRepository.findById(anyInt())).thenReturn(Optional.of(person));
        Person updatePerson = personService.updatePerson(person, anyInt());
        JSONAssert.assertEquals("CCC", updatePerson.getFirstName(), false);
        JSONAssert.assertEquals("student", updatePerson.getPersonType(), false);
        JSONAssert.assertEquals("AAA", updatePerson.getAddressLine1(), false);
        JSONAssert.assertEquals("BBB", updatePerson.getAddressLine2(), false);
        JSONAssert.assertEquals("USA", updatePerson.getCountry(), false);
        JSONAssert.assertEquals("AAA@gmail.com", updatePerson.getEmail(), false);
        JSONAssert.assertEquals("DDD", updatePerson.getLastName(), false);
        JSONAssert.assertEquals("Male", updatePerson.getGender(), false);
    }

    @Test
    public void test_findPersonByFirstNameAndPersonType_return_personList() throws Exception{
        when(personRepository.findPersonByFirstNameAndPersonType(anyString(), anyString()))
                .thenReturn(Arrays.asList(person));
        List<Person> persons = personService.findPersonByFirstNameAndPersonType(anyString(), anyString());
        JSONAssert.assertEquals("CCC", persons.get(0).getFirstName(), false);
        JSONAssert.assertEquals("student", persons.get(0).getPersonType(), false);
        JSONAssert.assertEquals("AAA", persons.get(0).getAddressLine1(), false);
        JSONAssert.assertEquals("BBB", persons.get(0).getAddressLine2(), false);
        JSONAssert.assertEquals("USA", persons.get(0).getCountry(), false);
        JSONAssert.assertEquals("AAA@gmail.com", persons.get(0).getEmail(), false);
        JSONAssert.assertEquals("DDD", persons.get(0).getLastName(), false);
        JSONAssert.assertEquals("Male", persons.get(0).getGender(), false);
    }

    @Test
    public void test_findPersonById_return_person() throws Exception{
        when(personRepository.findById(anyInt())).thenReturn(Optional.of(person));
        Person personById = personService.findPersonById(1);
        JSONAssert.assertEquals("CCC", personById.getFirstName(), false);
        JSONAssert.assertEquals("student", personById.getPersonType(), false);
        JSONAssert.assertEquals("AAA", personById.getAddressLine1(), false);
        JSONAssert.assertEquals("BBB", personById.getAddressLine2(), false);
        JSONAssert.assertEquals("USA", personById.getCountry(), false);
        JSONAssert.assertEquals("AAA@gmail.com", personById.getEmail(), false);
        JSONAssert.assertEquals("DDD", personById.getLastName(), false);
        JSONAssert.assertEquals("Male", personById.getGender(), false);
    }
}
