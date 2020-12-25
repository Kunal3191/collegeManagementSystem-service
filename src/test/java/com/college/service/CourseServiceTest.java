package com.college.service;

import com.college.model.Course;
import com.college.model.CourseMaster;
import com.college.model.Person;
import com.college.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {

    @InjectMocks
    private CourseService courseService;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private CourseMasterRepository courseMasterRepository;

    @Mock
    private AttendanceRepository attendanceRepository;

    @Mock
    private ExamRepository examRepository;


    private Person person;
    private Course course;
    private CourseMaster courseMaster;
    @BeforeEach
    public void setUp(){
        courseMaster = new CourseMaster();
        courseMaster.setCourseName("AAA");
        courseMaster.setDescription("BBB");
        person = new Person();
        person.setPersonId(1);
        person.setPersonType("student");
        person.setAddressLine1("AAA");
        person.setAddressLine2("BBB");
        person.setCountry("USA");
        person.setEmail("AAA@gmail.com");
        person.setFirstName("CCC");
        person.setLastName("DDD");
        person.setGender("Male");
        Set<Course> courses = new HashSet<>();
        course = new Course();
        course.setName("AAA");
        course.setTerm("2020");
        course.setDescription("BBB");
        course.setPublished("CCC");
        course.setFirstInstallment(100);
        course.setSecondInstallment(200);
        courses.add(course);
        person.setCourses(courses);
    }

    @Test
    public void test_getPersonCourse_return_courseSize(){
        when(personRepository.findById(anyInt())).thenReturn(Optional.of(person));
        Set<Course> courses = courseService.getPersonCourse(person.getPersonId());
        assertEquals(1, courses.size());
    }

    @Test
    public void test_getPersonCourse_return_setOfCourse() throws Exception{
        when(personRepository.findById(anyInt())).thenReturn(Optional.of(person));
        Set<Course> courses = courseService.getPersonCourse(person.getPersonId());
        JSONAssert.assertEquals("AAA", courses.stream().findAny().get().getName(), false);
    }

    @Test
    public void test_savePersonCourse_return_void() throws Exception{
        when(personRepository.findById(anyInt())).thenReturn(Optional.of(person));
        ArgumentCaptor<Person> captor = ArgumentCaptor.forClass(Person.class);
        courseService.savePersonCourse(anyInt(), course);
        verify(personRepository).findById(anyInt());
        verify(personRepository).save(captor.capture());
        JSONAssert.assertEquals("AAA",
                captor.getValue().getCourses().stream().findAny().get().getName(), false);
        JSONAssert.assertEquals("BBB",
                captor.getValue().getCourses().stream().findAny().get().getDescription(), false);
    }

    @Test
    public void test_updatePersonCourse_return_successfulMessage() throws Exception{
        ArgumentCaptor<Course> captor = ArgumentCaptor.forClass(Course.class);
        Map<String, String> stringMap = courseService.updatePersonCourse(anyInt(), course);
        verify(courseRepository).save(course);
        JSONAssert.assertEquals("data successfully update", stringMap.get("message"), false);
        verify(courseRepository).save(captor.capture());
        JSONAssert.assertEquals("AAA", captor.getValue().getName(), false);
        JSONAssert.assertEquals("BBB", captor.getValue().getDescription(), false);
    }

    @Test
    public void test_getAllCourses_return_setOfCourseMaster() throws Exception{
        when(personRepository.findById(anyInt())).thenReturn(Optional.of(person));
        when(courseMasterRepository.findByDepartment_DepartName(person.getProgram())).thenReturn(Set.of(courseMaster));
        Set<CourseMaster> courseMasters = courseService.getAllCourses(anyInt());
        JSONAssert.assertEquals("AAA", courseMasters.stream().findFirst().get().getCourseName(), false);
    }
}
