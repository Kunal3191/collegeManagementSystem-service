package com.college.service;


import com.college.model.Attendance;
import com.college.model.Person;
import com.college.repository.AttendanceRepository;
import com.college.repository.CourseRepository;
import com.college.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AttendanceServiceTest {

    @InjectMocks
    private AttendanceService attendanceService;

    @Mock
    private AttendanceRepository attendanceRepository;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private PersonRepository personRepository;

    private Attendance attendance;
    private Person person;
    @BeforeEach
    public void setUp(){
        attendance = new Attendance();
        attendance.setStatus("AAA");
        attendance.setCourseName("BBB");
        attendance.setPersonName("CCC");
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
        attendance.setPerson(person);
    }

    @Test
    public void test_getAllAttendance_return_attendanceList() throws Exception{
        when(attendanceRepository.findAttendanceByCourse_CourseId(anyInt())).thenReturn(Arrays.asList(attendance));
        List<Attendance> attendances = attendanceService.getAllAttendance(1, 1);
        JSONAssert.assertEquals("AAA", attendances.get(0).getStatus(), false);
        JSONAssert.assertEquals("BBB", attendances.get(0).getCourseName(), false);
        JSONAssert.assertEquals("CCC", attendances.get(0).getPersonName(), false);
        JSONAssert.assertEquals("FFF", attendances.get(0).getPerson().getFirstName(), false);
    }

    @Test
    public void test_saveAttendance_return_void() throws Exception{
        ArgumentCaptor<Attendance> captor = ArgumentCaptor.forClass(Attendance.class);
        attendanceService.saveAttendance(1, 1, attendance);
        verify(attendanceRepository).save(attendance);
        verify(attendanceRepository).save(captor.capture());
        JSONAssert.assertEquals("AAA", captor.getValue().getStatus(), false);
        JSONAssert.assertEquals("BBB", captor.getValue().getCourseName(), false);
        JSONAssert.assertEquals("CCC", captor.getValue().getPersonName(), false);
    }

    @Test
    public void test_deleteAttendance_return_void(){
        ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);
        attendanceService.deleteAttendance(1);
        verify(attendanceRepository).deleteById(anyInt());
        verify(attendanceRepository).deleteById(captor.capture());
        assertEquals(1, captor.getValue());
    }
}
