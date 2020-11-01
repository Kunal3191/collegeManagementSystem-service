package com.college.repository;

import com.college.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Integer> {
    Set<Exam> findExamByCourse_CourseId(int courseId);
}
