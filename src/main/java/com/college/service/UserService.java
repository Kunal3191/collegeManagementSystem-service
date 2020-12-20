package com.college.service;

import com.college.model.Course;
import com.college.model.User;
import com.college.repository.CourseRepository;
import com.college.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /*@Autowired
    private CourseRepository courseRepository;*/

  /*  @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;*/

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }


    public void saveUser(User user) {
        //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive("active");
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll().stream().sorted(Comparator.comparing(User::getFirstName)).collect(Collectors.toList());
        return users;
    }

    public User getUserById(int userId) {
        User user = null;
        Optional<User> userById = userRepository.findById(userId);
        if (userById.isPresent()) {
            user = userById.get();
        }
        return user;
    }

    public User updateUser(User user, int userId) {
        User existingUser = null;
        Optional<User> existingUserById = userRepository.findById(userId);
        if (existingUserById.isPresent()) {
            existingUser = existingUserById.get();
            if (user != null) {
                existingUser.setActive(user.getActive());
                existingUser.setPassword(user.getPassword());
                existingUser.setDateOfBirth(user.getDateOfBirth());
                existingUser.setEmail(user.getEmail());
                user.setFirstName(user.getFirstName());
                existingUser.setLastName(user.getLastName());
                existingUser.setRole(user.getRole());
            }
        }
        return existingUser;
    }

    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }
}
