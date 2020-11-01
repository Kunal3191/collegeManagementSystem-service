package com.college.repository;

import com.college.model.CourseMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseMasterRepository extends JpaRepository<CourseMaster, Integer> {
}
