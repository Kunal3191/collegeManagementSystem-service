package com.college.service;

import com.college.model.Course;
import com.college.model.Person;
import com.college.repository.CourseRepository;
import com.college.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private PersonRepository personRepository;

    public void savePersonCourse(int personId, Course course){
        Person person = null;
        Optional<Person> personWithId = personRepository.findById(personId);
        if(personWithId.isPresent()){
            person = personWithId.get();
            person.addCourses(course);
        }

    }
}
