package com.college.controller;

import com.college.model.Person;
import com.college.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.coyote.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PersonControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @Autowired
    private WebApplicationContext context;

    private Person person;

    @BeforeEach
    public void init(){
        person = new Person();
        person.setPersonId(1);
        person.setPersonType("student");
        person.setAddressLine1("AAA");
        person.setAddressLine2("BBBB");
        person.setCountry("USA");
        person.setEmail("AAA@gmail.com");
        person.setFirstName("CCC");
        person.setLastName("DDD");
        person.setGender("Male");
    }
    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    public void test_getPersonList_return_personList() throws Exception{
        when(personService.getPersonByPersonType("student")).thenReturn(Arrays.asList(person));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/person")
                .param("personType", "student")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("[*].firstName").value("CCC"))
                .andExpect(jsonPath("[*].lastName").value("DDD"))
                .andExpect(jsonPath("[*].gender").value("Male"))
                .andExpect(jsonPath("[*].email").value("AAA@gmail.com"))
                .andExpect(jsonPath("[*].country").value("USA"))
                .andExpect(jsonPath("[*].addressLine2").value("BBBB"))
                .andExpect(jsonPath("[*].addressLine1").value("AAA"))
                .andReturn();
    }

    @Test
    public void test_savePerson_return_map() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(person);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/person")
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"message\": \"user created successfully\"\n" +
                        "}"))
                .andReturn();
    }

    @Test
    public void test_getPersonByName_return_person() throws Exception{
        when(personService.findPersonByFirstNameAndPersonType(person.getFirstName(), person.getPersonType())).thenReturn(Arrays.asList(person));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/person/{firstName}", person.getFirstName())
                .param("personType", "student")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(jsonPath("[*].firstName").value("CCC"))
                .andExpect(jsonPath("[*].lastName").value("DDD"))
                .andExpect(jsonPath("[*].gender").value("Male"))
                .andExpect(jsonPath("[*].email").value("AAA@gmail.com"))
                .andExpect(jsonPath("[*].country").value("USA"))
                .andExpect(jsonPath("[*].addressLine2").value("BBBB"))
                .andExpect(jsonPath("[*].addressLine1").value("AAA"))
                .andReturn();
    }
}
