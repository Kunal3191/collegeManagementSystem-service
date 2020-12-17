package com.college.controller;

import com.college.model.Attendance;
import com.college.model.Exam;
import com.college.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class ExamController {

    @Autowired
    private ExamService examService;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/person/{personId}/exam", method = RequestMethod.GET)
    public Set<Exam> getAllExam(@PathVariable int personId, @RequestParam int courseId){
        Set<Exam> examSet = null;
        if(personId > 0 && courseId > 0){
            examSet = examService.getAllExam(courseId, personId);
        }
        return examSet;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/person/{personId}/exam", method = RequestMethod.POST)
    public Map<String, String> saveExam(@PathVariable int personId, @RequestParam int courseId, @RequestBody Exam exam){
        Map<String, String> hashMap = new HashMap<>();
        if(personId > 0 && courseId > 0){
            examService.saveExam(courseId, personId, exam);
            hashMap.put("message", "Data saved successfully");
            return hashMap;
        }
        hashMap.put("message", "data saving failed");
        return hashMap;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/person/{personId}/exam/{examId}", method = RequestMethod.DELETE)
    public Map<String, String> deleteExam(@PathVariable int personId, @PathVariable int examId){
        Map<String, String> hashMap = new HashMap<>();
        if(examId > 0){
            examService.deleteExam(examId);
            hashMap.put("message", "exam is deleted");
            return hashMap;
        }else {
            hashMap.put("message", "exam deletion failed");
            return hashMap;
        }
    }
}
