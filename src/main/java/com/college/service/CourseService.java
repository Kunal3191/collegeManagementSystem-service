package com.college.service;

import com.college.model.*;
import com.college.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
        Optional<Person> personWithId = personRepository.findById(personId);
        if(personWithId.isPresent()){
            person = personWithId.get();
            person.addCourses(course);
        }
        personRepository.save(person);

    }
    /*public Optional<Course> getPersonAllCourse(Person person, int courseId){
        Optional<Course> existingCourse = person.getCourses().stream().filter(course -> course.getCourseId() == courseId).findAny();
       return existingCourse;
    }*/
    public void deleteCourse(int courseId){
        if(courseId > 0){
            Course course = courseRepository.findById(courseId).get();
            Set<Attendance> attendances = course.getAttendances();
            attendances.forEach(attendance -> {
                if(attendance != null){
                    attendanceRepository.delete(attendance);
                }
            });
            Set<Exam> exams = course.getExams();
            exams.forEach(exam -> {
                if(exam != null){
                    examRepository.delete(exam);
                }
            });
            courseRepository.deleteById(courseId);
        }

    }

    public Set<CourseMaster> getAllCourses(){
        Set<CourseMaster> courseSet = courseMasterRepository.findAll().stream().collect(Collectors.toSet());
        return courseSet;
    }
}
