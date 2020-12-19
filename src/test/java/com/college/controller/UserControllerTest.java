package com.college.controller;

import com.college.model.User;
import com.college.repository.UserRepository;
import com.college.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.util.Arrays;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void newUser(){
        user = new User();
        user.setUserId(1);
        user.setFirstName("AAAAAA");
        user.setLastName("BBBBB");
        user.setDateOfBirth(null);
        user.setEmail("AAAAAAA@gmail.com");
        user.setPassword("test");
        user.setActive("active");
        user.setRole("admin");
        user.setMobileNumber("967798765");
    }
/*
    @BeforeAll
    public void clearDatabase(){
        userRepository.deleteAll();
    }*/
    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    public void test_login_return_user() throws Exception{
        when(userService.findByEmailAndPassword(user.getEmail(), user.getPassword()))
                .thenReturn(user);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/login")
                .param("email",user.getEmail())
                .param("password", user.getPassword())
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("firstName").value("AAAAAA"))
                .andExpect(jsonPath("lastName").value("BBBBB"))
                .andExpect(jsonPath("email").value("AAAAAAA@gmail.com"))
                .andExpect(jsonPath("password").value("test"))
                .andExpect(jsonPath("active").value("active"))
                .andExpect(jsonPath("role").value("admin"))
                .andExpect(jsonPath("mobileNumber").value("967798765"))
                .andReturn();
    }

    @Test
    public void test_getUsers_return_users() throws Exception{
        when(userService.getAllUsers()).thenReturn(Arrays.asList(user));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).
                andExpect(status().isOk())
                .andExpect(jsonPath("[*].firstName").value(hasItem("AAAAAA")))
                .andExpect(jsonPath("[*].lastName").value(hasItem("BBBBB")))
                .andExpect(jsonPath("[*].email").value(hasItem("AAAAAAA@gmail.com")))
                .andExpect(jsonPath("[*].password").value(hasItem("test")))
                .andExpect(jsonPath("[*].role").value(hasItem("admin")))
                .andExpect(jsonPath("[*].active").value(hasItem("active")))
                .andExpect(jsonPath("[*].mobileNumber").value(hasItem("967798765")))
                .andReturn();
    }

    @Test
    public void test_getUser_return_user() throws Exception{
        when(userService.getUserById(1)).thenReturn(user);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/{userId}", 1)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("AAAAAA"))
                .andExpect(jsonPath("$.lastName").value("BBBBB"))
                .andExpect(jsonPath("$.email").value("AAAAAAA@gmail.com"))
                .andExpect(jsonPath("$.password").value("test"))
                .andExpect(jsonPath("$.role").value("admin"))
                .andExpect(jsonPath("$.active").value("active"))
                .andExpect(jsonPath("$.mobileNumber").value("967798765"))
                .andReturn();
    }

    @Test
    public void test_create_User_return_status() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String jsonSting = mapper.writeValueAsString(user);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/signup").accept(MediaType.APPLICATION_JSON)
                //.content("{\"userId\":1,\"firstName\":\"anurag\",\"lastName\":\"yadav\",\"dateOfBirth\":\"2020-11-23T05:00:00.000+00:00\",\"email\":\"anurag@gmail.com\",\"password\":\"test\",\"active\":\"active\",\"role\":\"admin\",\"mobileNumber\":\"9677263751\"}")
                .content(jsonSting)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
    }

    /*
    * This is not working and have to figure it out how are we going to check the saved user in database.
     */
    /* @Test
    public void test_create_user_check_database_size() throws Exception{
        int repoSizeBeforeSaving = userRepository.findAll().size();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(user);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/signup")
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());

        List<User> userList = userRepository.findAll();
        assertEquals(userList.size(), repoSizeBeforeSaving + 1);
    }*/

    /**
     * update and delete functionality needs to check.....
     */
    /*@Test
    public void test_updateUser_return_user() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(user);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/users/{userId}", 1)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("firstName").value("AAAAAA"))
                .andReturn();
    }*/
}
