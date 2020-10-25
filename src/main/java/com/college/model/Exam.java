package com.college.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int examId;
    private String name;
    private int outOf;
    private double score;
    private Date examDate;

    public Exam() {
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOutOf() {
        return outOf;
    }

    public void setOutOf(int outOf) {
        this.outOf = outOf;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    private Course course;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "examId=" + examId +
                ", name='" + name + '\'' +
                ", outOf=" + outOf +
                ", score=" + score +
                ", examDate=" + examDate +
                ", course=" + course +
                '}';
    }
}
