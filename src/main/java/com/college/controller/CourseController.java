package com.college.controller;

import com.college.model.Course;
import com.college.model.CourseMaster;
import com.college.model.Person;
import com.college.service.CourseService;
import com.college.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private PersonService personService;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/person/{personId}/course", method = RequestMethod.GET)
    public Set<Course> getPersonCourse(@PathVariable int personId){
        Set<Course> courses = courseService.getPersonCourse(personId);
        return courses;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/person/{personId}/course", method = RequestMethod.POST)
    public Map<String, String> savePersonCourse(@PathVariable int personId, @RequestBody Course course){
        Map<String, String> hashMap = new HashMap<>();
        courseService.savePersonCourse(personId, course);
        hashMap.put("message", "Course saved");
        return hashMap;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/person/{personId}/course", method = RequestMethod.PUT)
    public Map<String, String> updatePersonCourse(@PathVariable int personId, @RequestBody Course course){
        Map<String, String> hashMap = new HashMap<>();
             hashMap = courseService.updatePersonCourse(personId, course);

        return hashMap;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/person/{personId}/course/{courseId}", method = RequestMethod.DELETE)
    public Map<String, String> deletePersonCourse(@PathVariable int personId, @PathVariable int courseId){
        Map<String, String> hashMap = new HashMap<>();
        if(courseId > 0){
            courseService.deleteCourse(personId, courseId);
            hashMap.put("message", "Record deleted successfully");
            return hashMap;
        }
        else
        {
            hashMap.put("message", "Record deletion failed");
            return hashMap;
        }
    }

//    @CrossOrigin(origins = "http://localhost:4200")
//    @RequestMapping(value = "/course", method = RequestMethod.GET)
//    public Set<CourseMaster> getAllCourses(){
//        Set<CourseMaster> courseSet = courseService.getAllCourses();
//        return courseSet;
//    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/courseMaster", method = RequestMethod.GET)
    public Set<CourseMaster> getAllCourse(){
        Set<CourseMaster> courseMasterSet = courseService.getAllCourses();
        return courseMasterSet;
    }
}
