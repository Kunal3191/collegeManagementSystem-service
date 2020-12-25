package com.college.controller;

import com.college.model.Attendance;
import com.college.service.AttendanceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.JsonPath;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.http.converter.json.Jackson2ObjectMapperBuilder.json;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AttendanceControllerTest {
    private MockMvc mockMvc;

    @MockBean
    private AttendanceService attendanceService;

    @Autowired
    private WebApplicationContext context;

    private Attendance attendance;

    @BeforeEach
    public void setUp(){
        attendance = new Attendance();
        attendance.setStatus("AAA");
        attendance.setCourseName("BBB");
        attendance.setPersonName("CCC");
    }

    @BeforeEach
    public void init(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    public void test_getAllAttendance_return_ListOfAttendance() throws Exception{
        when(attendanceService.getAllAttendance(anyInt(), anyInt())).thenReturn(Arrays.asList(attendance));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/person/{personId}/attendance", 1)
                .param("courseId",String.valueOf(1))
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(jsonPath("[*].status").value("AAA"))
                .andExpect(jsonPath("[*].courseName").value("BBB"))
                .andExpect(jsonPath("[*].personName").value("CCC"));
    }

    @Test
    public void test_saveAttendance_return_successMessage() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(attendance);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/person/{personId}/attendance", 1)
                .param("courseId", String.valueOf(1))
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"message\": \"data saved successfully\"\n" +
                        "}"));
    }
}
