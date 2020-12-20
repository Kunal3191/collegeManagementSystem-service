package com.college.service;

import com.college.model.User;
import com.college.repository.UserRepository;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void newUser(){
        user = new User();
        user.setUserId(1);
        user.setFirstName("AAA");
        user.setLastName("BBB");
        user.setDateOfBirth(null);
        user.setEmail("AAA@gmail.com");
        user.setPassword("test");
        user.setActive("active");
        user.setRole("admin");
        user.setMobileNumber("967798765");
    }

    @Test
    public void test_findByEmail_return_user() throws JSONException {
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        user = userService.findByEmail(user.getEmail());
        verify(userRepository).findByEmail(user.getEmail());
        verify(userRepository).findByEmail(captor.capture());
        JSONAssert.assertEquals("AAA@gmail.com", captor.getValue(), false);

        JSONAssert.assertEquals("AAA", user.getFirstName(), false);
        JSONAssert.assertEquals("BBB", user.getLastName(), false);
        JSONAssert.assertEquals("AAA@gmail.com", user.getEmail(), false);
        JSONAssert.assertEquals("test", user.getPassword(), false);
        JSONAssert.assertEquals("active", user.getActive(), false);
        JSONAssert.assertEquals("admin", user.getRole(), false);
    }

    @Test
    public void test_getAllUsers_return_usersList() throws JSONException {
        when(userRepository.findAll()).thenReturn(Arrays.asList(user));
        List<User> users = userService.getAllUsers();
        JSONAssert.assertEquals("AAA", users.get(0).getFirstName(), false);
        JSONAssert.assertEquals("BBB", users.get(0).getLastName(), false);
        JSONAssert.assertEquals("AAA@gmail.com", users.get(0).getEmail(), false);
        JSONAssert.assertEquals("test", users.get(0).getPassword(), false);
        JSONAssert.assertEquals("active", users.get(0).getActive(), false);
        JSONAssert.assertEquals("admin", users.get(0).getRole(), false);
        verify(userRepository).findAll();
    }

    @Test
    public void test_findByEmailAndPassword_return_user() throws JSONException {
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> captor1 = ArgumentCaptor.forClass(String.class);
        when(userRepository.findByEmailAndPassword(anyString(), anyString())).thenReturn(user);
        user = userService.findByEmailAndPassword(user.getEmail(), user.getPassword());
        JSONAssert.assertEquals("AAA", user.getFirstName(), false);
        JSONAssert.assertEquals("BBB", user.getLastName(), false);
        JSONAssert.assertEquals("AAA@gmail.com", user.getEmail(), false);
        JSONAssert.assertEquals("test", user.getPassword(), false);
        JSONAssert.assertEquals("active", user.getActive(), false);
        JSONAssert.assertEquals("admin", user.getRole(), false);
        verify(userRepository).findByEmailAndPassword(user.getEmail(), user.getPassword());
        verify(userRepository).findByEmailAndPassword(captor.capture(), captor1.capture());
        JSONAssert.assertEquals("AAA@gmail.com", captor.getValue(), false);
        JSONAssert.assertEquals("test", captor1.getValue(), false);
    }

    @Test
    public void test_saveUser_return_void() throws JSONException {
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        userService.saveUser(user);
        verify(userRepository).save(user);
        verify(userRepository).save(captor.capture());
        JSONAssert.assertEquals(user.getFirstName(), captor.getValue().getFirstName(), false);
        JSONAssert.assertEquals(user.getLastName(), captor.getValue().getLastName(), false);
        JSONAssert.assertEquals(user.getPassword(), captor.getValue().getPassword(), false);
        JSONAssert.assertEquals(user.getEmail(), captor.getValue().getEmail(), false);
    }

    @Test
    public void test_updateUser_return_user() throws JSONException {
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        user = userService.updateUser(user, user.getUserId());
        JSONAssert.assertEquals("AAA", user.getFirstName(), false);
        JSONAssert.assertEquals("BBB", user.getLastName(), false);
        JSONAssert.assertEquals("AAA@gmail.com", user.getEmail(), false);
        JSONAssert.assertEquals("test", user.getPassword(), false);
        JSONAssert.assertEquals("active", user.getActive(), false);
        JSONAssert.assertEquals("admin", user.getRole(), false);
        verify(userRepository).findById(anyInt());
    }

    @Test
    public void test_deleteUser_return_void(){
        ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);
        userService.deleteUser(anyInt());
        verify(userRepository).deleteById(anyInt());
        verify(userRepository).deleteById(captor.capture());
        assertEquals(0,captor.getValue());
    }

    @Test
    public void test_getUserById_return_user() throws JSONException {
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        user = userService.getUserById(anyInt());
        JSONAssert.assertEquals("AAA", user.getFirstName(), false);
        JSONAssert.assertEquals("BBB", user.getLastName(), false);
        JSONAssert.assertEquals("AAA@gmail.com", user.getEmail(), false);
        JSONAssert.assertEquals("test", user.getPassword(), false);
        JSONAssert.assertEquals("active", user.getActive(), false);
        JSONAssert.assertEquals("admin", user.getRole(), false);
        verify(userRepository).findById(anyInt());
    }
}
