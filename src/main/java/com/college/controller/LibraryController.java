package com.college.controller;

import com.college.model.Library;
import com.college.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @RequestMapping(value = "/library", method = RequestMethod.POST)
    public Map<String,String> saveBook(@RequestBody Library library){
        Map<String, String> map = new HashMap<>();
        String string = libraryService.saveBook(library);
        if(string.equalsIgnoreCase("data saved successfully")){
            map.put("message", "data saved successfully");
            return map;
        }
        map.put("message", "data saving failed");
        return map;
    }

    @RequestMapping(value = "/library", method = RequestMethod.GET)
    public Library findBook(@RequestParam String bookName){
        Library library = null;
        if(bookName != null){
            library = libraryService.findBookByName(bookName);
        }
        return library;
    }

    @RequestMapping(value = "/library", method = RequestMethod.GET)
    public Library findBookOrder(@RequestParam String bookName, @RequestParam String authorName){
        Library library = null;
        if(bookName != null && authorName != null){
            library = libraryService.findOrder(bookName, authorName);
        }
        return library;
    }
}
