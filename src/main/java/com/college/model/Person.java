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
    private String addressLine1;
    private String addressLine2;
    private String state;
    private String country;
    private String zipCode;
    private String email;
    private Date dateOfBirth;
    private String personType;
    private String mobileNumber;

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

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "person_course", joinColumns=@JoinColumn(name = "person_id"), inverseJoinColumns =@JoinColumn(name = "course_id"))
    private Set<Course> courses;

    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER)
    //@JoinColumn(insertable = false, updatable = false)
    private List<Attendance> attendances;

    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER)
    private Set<Exam> exams;

    public Set<Exam> getExams() {
        return exams;
    }

    public void setExams(Exam exam) {
        this.exams.add(exam);
    }

    public Set<Course> getCourses() {
        return courses;
    }
    public void addCourses(Course course) {
        this.courses.add(course);
    }
    public void removeCourse(Course course){
        this.courses.remove(course);
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(Attendance attendance) {
        this.attendances.add(attendance);
    }


    /* @Override
    public String toString() {
        return "Person{" +
                "PersonId=" + PersonId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", gender='" + gender + '\'' +
                ", program='" + program + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", personType='" + personType + '\'' +
                ", mobileNumber=" + mobileNumber +
                ", courses=" + courses +
                ", attendances=" + attendances +
                '}';
    }*/
}
