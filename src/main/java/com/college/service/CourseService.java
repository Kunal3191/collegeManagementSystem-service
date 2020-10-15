package com.college.service;

import com.college.model.Course;
import com.college.model.Person;
import com.college.repository.CourseRepository;
import com.college.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private PersonRepository personRepository;

    public Set<Course> getPersonCourse(int personId){
        Person person = null;
        Set<Course> courses= null;
        if(personId > 0){
            Optional<Person> personPresent = personRepository.findById(personId);
            if(personPresent.isPresent()){
                person = personPresent.get();
            }
        }
        if(person != null && person.getCourses() != null){
            courses = person.getCourses();
        }
        return courses;
    }

    public void savePersonCourse(int personId, Course course){
        Person person = null;
        Optional<Person> personWithId = personRepository.findById(personId);
        if(personWithId.isPresent()){
            person = personWithId.get();
            person.addCourses(course);
        }

    }
    /*public Optional<Course> getPersonAllCourse(Person person, int courseId){
        Optional<Course> existingCourse = person.getCourses().stream().filter(course -> course.getCourseId() == courseId).findAny();
       return existingCourse;
    }*/
    public void deleteCourse(int courseId){
        courseRepository.deleteById(courseId);
    }
}
