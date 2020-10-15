package com.college.model;

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
    private String paperName;
    private double score;
    private int total;
    private Date examConducted;

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

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Date getExamConducted() {
        return examConducted;
    }

    public void setExamConducted(Date examConducted) {
        this.examConducted = examConducted;
    }

    @ManyToMany(mappedBy = "courses")
    private Set<Person> persons;

    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", name='" + name + '\'' +
                ", term='" + term + '\'' +
                ", description='" + description + '\'' +
                ", published='" + published + '\'' +
                ", paperName='" + paperName + '\'' +
                ", score=" + score +
                ", total=" + total +
                ", examConducted=" + examConducted +
                ", persons=" + persons +
                '}';
    }
}
