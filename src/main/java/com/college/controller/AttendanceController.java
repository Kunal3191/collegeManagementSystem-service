package com.college.controller;

import com.college.model.Attendance;
import com.college.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/person/{personId}/attendance", method = RequestMethod.GET)
    public List<Attendance> getAllAttendance(@PathVariable int personId, @RequestParam int courseId){
        List<Attendance> attendanceSet = null;
        if(personId > 0 && courseId > 0){
            attendanceSet = attendanceService.getAllAttendance(courseId, personId);
        }
        return attendanceSet;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/person/{personId}/attendance", method = RequestMethod.POST)
    public Map<String, String> saveAttendance(@PathVariable int personId, @RequestParam int courseId, @RequestBody Attendance attendance){
        Map<String, String> map = new HashMap<>();
        if(personId > 0 && courseId > 0){
            attendanceService.saveAttendance(courseId, personId, attendance);
            map.put("message", "data saved successfully");
        }
        else{
            map.put("message", "data saving failed");
        }
        return map;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/person/{personId}/attendance/{attendanceId}", method = RequestMethod.DELETE)
    public Map<String, String> deleteAttendance(@PathVariable int personId, @PathVariable int attendanceId){
        Map<String, String> map = new HashMap<>();
        /*if(personId > 0 && courseId > 0){
            attendanceService.saveAttendance(courseId, personId, attendance);
            return "Data saved successfully";
        }*/
        if(attendanceId > 0){
            attendanceService.deleteAttendance(attendanceId);
            map.put("message", "record deleted");
        }
        else{
            map.put("message", "record deletion failed");
        }
        return map;
    }
}
