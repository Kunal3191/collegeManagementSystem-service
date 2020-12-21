package com.college.controller;

import com.college.model.Course;
import com.college.model.CourseMaster;
import com.college.service.CourseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.*;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CourseControllerTest {

    @MockBean
    private CourseService courseService;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    private Course course;
    Set<Course> courses = new HashSet<>();
    @BeforeEach
    public void init(){
        course = new Course();
        course.setName("AAA");
        course.setTerm("2020");
        course.setDescription("BBB");
        course.setPublished("CCC");
        course.setFirstInstallment(100);
        course.setSecondInstallment(200);
        courses.add(course);
    }
    private CourseMaster courseMaster;
    Set<CourseMaster> masters = new HashSet<>();
    @BeforeEach
    public void initialization(){
        courseMaster = new CourseMaster();
        courseMaster.setCourseName("AAA");
        courseMaster.setDescription("DDD");
        masters.add(courseMaster);
    }

    @Test
    public void test_getPersonCourse_return_courses() throws Exception{
        when(courseService.getPersonCourse(anyInt())).thenReturn(courses);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/person/{personId}/course", 1)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("[*].name").value("AAA"))
                .andExpect(jsonPath("[*].term").value("2020"))
                .andExpect(jsonPath("[*].description").value("BBB"))
                .andExpect(jsonPath("[*].published").value("CCC"))
                .andExpect(jsonPath("[*].firstInstallment").value(100.0))
                .andExpect(jsonPath("[*].secondInstallment").value(200.0))
                .andReturn();
    }

    @Test
    public void test_savePersonCourse_return_map() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(course);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/person/{personId}/course", 1)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(content().json("{\n" +
                "    \"message\": \"Course saved\"\n" +
                "}"))
        .andReturn();
    }

    @Test
    public void test_getAllCourse_return_courses() throws Exception{
        when(courseService.getAllCourses(anyInt())).thenReturn(masters);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/courseMaster/{personId}", 1)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(jsonPath("[*].courseName").value("AAA"))
                .andExpect(jsonPath("[*].description").value("DDD"))
                .andReturn();
    }

    @Test
    public void test_deletePersonCourse_return_success_map() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/person/{personId}/course/{courseId}", 1, 1)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"message\": \"Record deleted successfully\"\n" +
                        "}"))
                .andReturn();
    }

    @Test
    public void test_deletePersonCourse_return_failure_map() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/person/{personId}/course/{courseId}", 1, 0)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"message\": \"Record deletion failed\"\n" +
                        "}"))
                .andReturn();
    }
}