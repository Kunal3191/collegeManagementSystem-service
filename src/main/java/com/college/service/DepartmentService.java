package com.college.service;

import com.college.model.Department;
import com.college.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments(){
        List<Department> departmentList = departmentRepository.findAll();
        return departmentList;
    }
}
