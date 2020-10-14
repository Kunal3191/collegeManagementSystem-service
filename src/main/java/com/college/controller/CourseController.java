package com.college.controller;

import com.college.model.Course;
import com.college.model.Person;
import com.college.service.CourseService;
import com.college.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/person/{personId}/course", method = RequestMethod.POST)
    public ResponseEntity savePersonCourse(@PathVariable int personId, @RequestBody Course course){
        courseService.savePersonCourse(personId, course);
        return ResponseEntity.status(201).body("Course saved.");
    }
    /*@RequestMapping(value = "/person/{personId}/course/{{courseId}}", method = RequestMethod.DELETE)
    public void deletePersonCourse(@PathVariable int personId, @PathVariable int courseId){
        Person person = personService.findPersonById(personId);
        if(person.getCourses() != null){
            person.getCourses().;
        }
        courseService.savePersonCourse(personId, course);
    }*/
}
