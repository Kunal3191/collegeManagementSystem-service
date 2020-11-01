package com.college.controller;

import com.college.model.Attendance;
import com.college.model.Exam;
import com.college.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public String saveExam(@PathVariable int personId, @RequestParam int courseId, @RequestBody Exam exam){
        if(personId > 0 && courseId > 0){
            examService.saveExam(courseId, personId, exam);
            return "Data saved successfully";
        }
        return "saving failed";
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/person/{personId}/exam/{examId}", method = RequestMethod.DELETE)
    public String deleteExam(@PathVariable int personId, @RequestParam int courseId, @PathVariable int examId){
        if(examId > 0){
            examService.deleteExam(examId);
            return "Record deleted";
        }
        return "Record deletion failed";
    }
}
