package com.college.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName;
    private String lastName;
    private Date issueDate;
    private Date lastReturnDate;
    private String status;
    private int noOfBookOrdered;

    public Library() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getLastReturnDate() {
        return lastReturnDate;
    }

    public void setLastReturnDate(Date lastReturnDate) {
        this.lastReturnDate = lastReturnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNoOfBookOrdered() {
        return noOfBookOrdered;
    }

    public void setNoOfBookOrdered(int noOfBookOrdered) {
        this.noOfBookOrdered = noOfBookOrdered;
    }


    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;



    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Library{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", issueDate=" + issueDate +
                ", lastReturnDate=" + lastReturnDate +
                ", status='" + status + '\'' +
                ", noOfBookOrdered=" + noOfBookOrdered +
                ", book=" + book +
                '}';
    }
}
