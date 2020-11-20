package com.college.controller;

import com.college.model.Department;
import com.college.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/department", method = RequestMethod.GET)
    public List<Department> getAllDepartments(){
        List<Department> departmentList = departmentService.getAllDepartments();
        return departmentList;
    }
}
