package com.college.service;

import com.college.model.Term;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;

@Service
public class TermService {

    public Term getTerm(){
        Term term = new Term();
        LocalDate localDate = LocalDate.now();
        Month month = localDate.getMonth();
        if(month.equals(Month.JANUARY) || month.equals(Month.FEBRUARY) || month.equals(Month.MARCH) ||
                month.equals(Month.APRIL)){
            term.setTermType("Winter");
        }
        if(month.equals(Month.MAY) || month.equals(Month.JUNE) || month.equals(Month.JULY) ||
                month.equals(Month.AUGUST)){
            term.setTermType("Summer");
        }
        if(month.equals(Month.SEPTEMBER) || month.equals(Month.OCTOBER) || month.equals(Month.NOVEMBER) ||
                month.equals(Month.DECEMBER)){
            term.setTermType("Fall");
        }
        term.setYear(localDate.getYear());

        return term;

    }
}
