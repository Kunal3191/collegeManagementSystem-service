package com.college.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String departName;

    public Department() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    @OneToMany(mappedBy = "department")
    private Set<CourseMaster> courseMasters ;

    public Set<CourseMaster> getCourseMasters() {
        return courseMasters;
    }

    public void addCourseMasters(CourseMaster courseMaster) {
        this.courseMasters.add(courseMaster);
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departName='" + departName + '\'' +
                ", courseMasters=" + courseMasters +
                '}';
    }
}
