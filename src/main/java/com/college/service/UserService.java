package com.college.service;

import com.college.model.Course;
import com.college.model.User;
import com.college.repository.CourseRepository;
import com.college.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }public User findByEmailAndPassword(String email, String password){
        return userRepository.findByEmailAndPassword(email, password);
    }


//    public void saveUser(User user){
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        user.setActive("1");
//        /*Course course = new Course();
//        course.setName("Java");
//        course.setPublished("Yes");
//        course.setTerm("Fall 2020");
//        courseRepository.save(course);
//        user.addCourse(course);*/
//        userRepository.save(user);
//    }
}
