package com.college.model;

public class Term {

    private String termType;
    private int year;


    public Term() {
    }

    public String getTermType() {
        return termType;
    }

    public void setTermType(String termType) {
        this.termType = termType;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Term{" +
                "termType='" + termType + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
