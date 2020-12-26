package com.college.controller;

import com.college.model.Exam;
import com.college.service.ExamService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.coyote.Request;
import org.aspectj.lang.annotation.Before;
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

import java.util.Date;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ExamControllerTest {

    @MockBean
    private ExamService examService;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void init(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    private Exam exam;
    @BeforeEach
    public void setUp(){
        exam = new Exam();
        exam.setExamId(1);
        exam.setName("AAA");
        exam.setOutOf(100);
        exam.setScore(89.0);
        exam.setPersonName("BBB");
        exam.setCourseName("CCC");
    }

    @Test
    public void test_getAllExam_return_setOfExams() throws Exception{
        when(examService.getAllExam(anyInt(), anyInt())).thenReturn(Set.of(exam));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/person/{personId}/exam", 1)
                .param("courseId", String.valueOf(1))
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("[*].name").value("AAA"))
                .andExpect(jsonPath("[*].outOf").value(100))
                .andExpect(jsonPath("[*].score").value(89.0))
                .andExpect(jsonPath("[*].personName").value("BBB"))
                .andExpect(jsonPath("[*].courseName").value("CCC"))
                .andReturn();
    }

    @Test
    public void test_saveExam_return_successMessage() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(exam);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/person/{personId}/exam", 1)
                .param("courseId",String.valueOf(1))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"message\": \"Data saved successfully\"\n" +
                        "}"))
                .andReturn();
    }

    @Test
    public void test_deleteExam_return_successMessage() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/person/{personId}/exam/{examId}", 1, 1)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"message\": \"exam is deleted\"\n" +
                        "}"))
                .andReturn();
    }
}
