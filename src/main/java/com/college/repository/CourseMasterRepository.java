package com.college.repository;

import com.college.model.CourseMaster;
import com.college.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CourseMasterRepository extends JpaRepository<CourseMaster, Integer> {
    Set<CourseMaster> findByDepartment_DepartName(String departName);
}
