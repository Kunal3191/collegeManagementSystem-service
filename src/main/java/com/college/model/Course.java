package com.college.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int courseId;
    private String name;
    private String term;
    private String description;
    private String published;

    /*for fee*/
    private double firstInstallment;
    private double secondInstallment;
    private Date firstInstallmentDate;
    private Date secondInstallmentDate;
    private double totalFee;

    public Course() {
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getFirstInstallment() {
        return firstInstallment;
    }

    public void setFirstInstallment(double firstInstallment) {
        this.firstInstallment = firstInstallment;
    }

    public double getSecondInstallment() {
        return secondInstallment;
    }

    public void setSecondInstallment(double secondInstallment) {
        this.secondInstallment = secondInstallment;
    }

    public Date getFirstInstallmentDate() {
        return firstInstallmentDate;
    }

    public void setFirstInstallmentDate(Date firstInstallmentDate) {
        this.firstInstallmentDate = firstInstallmentDate;
    }

    public Date getSecondInstallmentDate() {
        return secondInstallmentDate;
    }

    public void setSecondInstallmentDate(Date secondInstallmentDate) {
        this.secondInstallmentDate = secondInstallmentDate;
    }

    public double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
    }

    @JsonIgnore
    @ManyToMany(mappedBy = "courses")
    private Set<Person> persons;

    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private Set<Attendance> attendances;

    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private Set<Exam> exams;

    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }

    public Set<Attendance> getAttendances() {
        return attendances;
    }

    public void addAttendances(Attendance attendance) {
        this.attendances.add(attendance);
    }

    public Set<Exam> getExams() {
        return exams;
    }

    public void addExams(Exam exam) {
        this.exams.add(exam);
    }

    /*@Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", name='" + name + '\'' +
                ", term='" + term + '\'' +
                ", description='" + description + '\'' +
                ", published='" + published + '\'' +
                ", firstInstallment=" + firstInstallment +
                ", secondInstallment=" + secondInstallment +
                ", firstInstallmentDate=" + firstInstallmentDate +
                ", secondInstallmentDate=" + secondInstallmentDate +
                ", totalFee=" + totalFee +
                ", persons=" + persons +
                ", attendances=" + attendances +
                ", exams=" + exams +
                '}';
    }*/
}
