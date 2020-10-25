package com.college.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int PersonId;
    private String firstName;
    private String lastName;
    private String middleName;
    private String gender;
    private String program;
    private String address;
    private String email;
    private Date dateOfBirth;
    private String personType;

    public Person() {
    }

    public int getPersonId() {
        return PersonId;
    }

    public void setPersonId(int personId) {
        PersonId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }




    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "person_course", joinColumns=@JoinColumn(name = "person_id"), inverseJoinColumns =@JoinColumn(name = "course_id"))
    private Set<Course> courses;

    @OneToMany(mappedBy = "person")
    //@JoinColumn(insertable = false, updatable = false)
    private List<Attendance> attendances;

    public Set<Course> getCourses() {
        return courses;
    }

    public void addCourses(Course course) {
        this.courses.add(course);
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(Attendance attendance) {
        this.attendances.add(attendance);
    }
}
