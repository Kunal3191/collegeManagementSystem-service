package com.college.service;

import com.college.model.Attendance;
import com.college.model.Course;
import com.college.model.Person;
import com.college.repository.AttendanceRepository;
import com.college.repository.CourseRepository;
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
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<Attendance> getAllAttendance(int courseId, int personId){
        List<Attendance> attendanceSet = null;
        if(courseId > 0 && personId > 0){
            /*String firstName = personRepository.findById(personId).map(Person::getFirstName).get();
            String courseName = courseRepository.findById(courseId).map(Course::getName).get();*/
            //attendanceSet = attendanceRepository.findByCourseNameAndPersonName(courseName, firstName);
            attendanceSet = attendanceRepository.findAttendanceByCourse_CourseId(courseId).stream().filter(attendance -> attendance.getPerson().getPersonId() == personId)
            .collect(Collectors.toList());
        }
        return attendanceSet;
    }
    public void saveAttendance(int courseId, int personId, Attendance attendance){
        Person person = null;
        Course course = null;
        if(courseId > 0 && personId > 0){
            Optional<Person> personById= personRepository.findById(personId);
            Optional<Course> courseById = courseRepository.findById(courseId);
            if(personById.isPresent() && courseById.isPresent()){
                person = personById.get();
                attendance.setPersonName(person.getFirstName());
                course = courseById.get();
                attendance.setCourseName(course.getName());
                if(attendance != null){
                    attendance.setPerson(person);
                    attendance.setCourse(course);
                }

            }
        }

        attendanceRepository.save(attendance);
    }

    public void deleteAttendance(int attendanceId){
        if(attendanceId > 0){
            attendanceRepository.deleteById(attendanceId);
        }
    }
}
