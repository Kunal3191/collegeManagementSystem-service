package com.college.service;

import com.college.model.*;
import com.college.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CourseMasterRepository courseMasterRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private ExamRepository examRepository;

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
        courseRepository.save(course);
        Optional<Person> personWithId = personRepository.findById(personId);
        if(personWithId.isPresent()){
            person = personWithId.get();
            person.addCourses(course);

        }
        personRepository.save(person);

    }
    public Map<String, String> updatePersonCourse(int personId, Course course){
        Map<String, String> hashMap = new HashMap<>();
        if(course != null) {
            courseRepository.save(course);
            hashMap.put("message", "data successfully update");
        }
        else{
            hashMap.put("message", "data update failed");
        }
    return hashMap;
    }
    /*public Optional<Course> getPersonAllCourse(Person person, int courseId){
        Optional<Course> existingCourse = person.getCourses().stream().filter(course -> course.getCourseId() == courseId).findAny();
       return existingCourse;
    }*/
    public void deleteCourse(int personId, int courseId){
        if(courseId > 0){
            Person person = personRepository.findById(personId).get();
            person.getCourses().stream().filter(course -> course.getCourseId() == courseId)
                .forEach(course -> {
                course.getAttendances().forEach(attendance -> attendanceRepository.delete(attendance));
            });
            person.getCourses().stream().filter(course -> course.getCourseId() == courseId)
                    .forEach(course -> {
                course.getExams().forEach(exam -> examRepository.delete(exam));
            });
            person.removeCourse(courseRepository.getOne(courseId));
            courseRepository.deleteById(courseId);
        }

    }

    public Set<CourseMaster> getAllCourses(int personId){
        Set<CourseMaster> courseSet = null;
        Person person = null;
        Optional<Person> personOptional = personRepository.findById(personId);
        if(personOptional.isPresent()) {
            person = personOptional.get();
            String program = person.getProgram();
            courseSet = courseMasterRepository.findByDepartment_DepartName(program).stream().collect(Collectors.toSet());
        }
        return courseSet;
    }
}
