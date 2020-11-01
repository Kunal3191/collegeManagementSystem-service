package com.college.service;

import com.college.model.Attendance;
import com.college.model.Course;
import com.college.model.Exam;
import com.college.model.Person;
import com.college.repository.AttendanceRepository;
import com.college.repository.CourseRepository;
import com.college.repository.ExamRepository;
import com.college.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CourseRepository courseRepository;

    public Set<Exam> getAllExam(int courseId, int personId){
        Set<Exam> examSet = null;
        if(courseId > 0 && personId > 0){
            String firstName = personRepository.findById(personId).map(Person::getFirstName).get();
            String courseName = courseRepository.findById(courseId).map(Course::getName).get();
            examSet = examRepository.findExamByCourse_CourseId(courseId).stream().filter(attendance -> attendance.getPerson().getPersonId() == personId)
                    .collect(Collectors.toSet());
        }
        return examSet;
    }
    public void saveExam(int courseId, int personId, Exam exam){
        Person person = null;
        Course course = null;
        if(courseId > 0 && personId > 0){
            Optional<Person> personById= personRepository.findById(personId);
            Optional<Course> courseById = courseRepository.findById(courseId);
            if(personById.isPresent() && courseById.isPresent()){
                person = personById.get();
                exam.setPersonName(person.getFirstName());
                course = courseById.get();
                exam.setCourseName(course.getName());
                if(exam != null){
                    exam.setPerson(person);
                    exam.setCourse(course);
                }

            }
        }

        examRepository.save(exam);
    }

    public void deleteExam(int examId){
        if(examId > 0){
            examRepository.deleteById(examId);
        }
    }
}
