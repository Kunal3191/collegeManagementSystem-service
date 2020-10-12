package com.college.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String term;
    private String published;

//    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "courseList")
//    private List<User> userList = new ArrayList<>();
//    public Course() {
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

//    public List<User> getUserList() {
//        return userList;
//    }
//
//    public void setUserList(User user) {
//        this.userList.add(user);
//    }


    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", term='" + term + '\'' +
                ", published='" + published + '\'' +
                '}';
    }
}
