package com.college.repository;

import com.college.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
    //@Query("select attendance from Attendance attendance where attendance.course.courseId = :courseId and attendance.person.PersonId = :personid")
    //List<Attendance> findByCourseNameAndPersonName(String courseName, String personName);
    List<Attendance> findAttendanceByCourse_CourseId(int courseId);
}
