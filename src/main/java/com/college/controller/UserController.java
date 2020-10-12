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
//    @CrossOrigin(origins = "http://localhost:4200")
//    @RequestMapping(value = "/signup", method = RequestMethod.POST)
//    public ResponseEntity createUser(@RequestBody User user){
//        ModelAndView model = new ModelAndView();
//        User userExists = userService.findByEmail(user.getEmail());
//
//        if(userExists != null) {
//            return ResponseEntity.status(400).body("This emil already in use. Please Use other email");
//        }
//        else {
//            user.setPassword("test");
//            userService.saveUser(user);
//            return ResponseEntity.status(201).body("User created Successfully");
//        }
//
//    }
}
