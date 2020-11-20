package com.college.controller;

import com.college.model.Term;
import com.college.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TermController {

    @Autowired
    private TermService termService;

    @RequestMapping(value = "/term", method = RequestMethod.GET)
    public Term getCurrentTerm(){
        Term term = termService.getTerm();
        return term;
    }
}
