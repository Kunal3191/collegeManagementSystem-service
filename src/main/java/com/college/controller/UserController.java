package com.college.controller;

import com.college.model.User;
import com.college.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Object login(@RequestParam String email, String password) {
        User user = userService.findByEmailAndPassword(email, password);
        if(user != null)
            return user;
        else
            return "User not found";
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public Map<String, String> createUser(@RequestBody User user){
        Map<String, String> hashMap = new HashMap<>();
        //ModelAndView model = new ModelAndView();
        User userExists = userService.findByEmail(user.getEmail());

        if(userExists != null) {
            hashMap.put("message", "This email already in use. Please use different email");
            return hashMap;
        }
        else {
            user.setPassword("test");
            userService.saveUser(user);
            hashMap.put("message", "User created Successfully");
            return hashMap;
        }

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getUsers(){
        List<User> users = userService.getAllUsers();
        return users;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable int userId){
        User user = null;
        if(userId > 0){
            user = userService.getUserById(userId);
        }
        return user;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/users/{userId}", method = RequestMethod.PUT)
    public User updateUser(@RequestBody User user, @PathVariable int userId){
        User existUser = null;
        if(userId > 0){
            existUser = userService.updateUser(user, userId);
        }
        return existUser;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/users/{userId}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable int userId){
        if(userId > 0){
            userService.deleteUser(userId);
            return "user is deleted.";
        }
        else{
            return "user deletion failed.";
        }
    }
}
