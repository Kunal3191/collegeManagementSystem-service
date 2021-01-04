package com.college.service;

import com.college.model.Attendance;
import com.college.model.Exam;
import com.college.model.Person;
import com.college.repository.CourseRepository;
import com.college.repository.ExamRepository;
import com.college.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExamServiceTest {

    @InjectMocks
    private ExamService examService;

    @Mock
    private ExamRepository examRepository;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private PersonRepository personRepository;

    private Exam exam;
    private Person person;
    @BeforeEach
    public void setUp(){
        exam = new Exam();
        exam.setExamId(1);
        exam.setName("AAA");
        exam.setOutOf(100);
        exam.setScore(89.0);
        exam.setPersonName("BBB");
        exam.setCourseName("CCC");

        person = new Person();
        person.setPersonId(1);
        person.setPersonType("student");
        person.setAddressLine1("DDD");
        person.setAddressLine2("EEE");
        person.setCountry("USA");
        person.setEmail("DDD@gmail.com");
        person.setFirstName("FFF");
        person.setLastName("GGG");
        person.setGender("Male");
        exam.setPerson(person);
    }

    @Test
    public void test_getAllExam_return_setOfExams() throws Exception{
        when(examRepository.findExamByCourse_CourseId(anyInt())).thenReturn(Set.of(exam));
        Set<Exam> examSet = examService.getAllExam(1, 1);
        JSONAssert.assertEquals("AAA", examSet.stream().findFirst().get().getName(), false);
        JSONAssert.assertEquals("100", String.valueOf(examSet.stream().findFirst().get().getOutOf()), false);
        JSONAssert.assertEquals("BBB", examSet.stream().findFirst().get().getPersonName(), false);
        JSONAssert.assertEquals("CCC", examSet.stream().findFirst().get().getCourseName(), false);
    }

    @Test
    public void test_saveExam_return_void() throws Exception{
        ArgumentCaptor<Exam> captor = ArgumentCaptor.forClass(Exam.class);
        examService.saveExam(1, 1, exam);
        verify(examRepository).save(exam);
        verify(examRepository).save(captor.capture());
        JSONAssert.assertEquals("AAA", captor.getValue().getName(), false);
        JSONAssert.assertEquals("BBB", captor.getValue().getPersonName(), false);
        JSONAssert.assertEquals("CCC", captor.getValue().getCourseName(), false);
    }

    @Test
    public void test_deleteExam_return_void(){
        ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);
        examService.deleteExam(1);
        verify(examRepository).deleteById(anyInt());
        verify(examRepository).deleteById(captor.capture());
        assertEquals(1, captor.getValue());
    }
}
