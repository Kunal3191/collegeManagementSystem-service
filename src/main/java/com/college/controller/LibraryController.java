package com.college.controller;

import com.college.model.Book;
import com.college.model.Library;
import com.college.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/library", method = RequestMethod.POST)
    public Map<String,String> addBook(@RequestBody Book book){
        Map<String, String> map = new HashMap<>();
        String string = libraryService.saveBook(book);
        if(string.equalsIgnoreCase("data saved successfully")){
            map.put("message", "data saved successfully");
            return map;
        }
        map.put("message", "data saving failed");
        return map;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/library/status", method = RequestMethod.GET)
    public List<Library> viewOrder(@RequestParam String bookName){
        List<Library> libraryList = libraryService.getBookByStatus(bookName);
        return libraryList;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/library/{id}", method = RequestMethod.PUT)
    public Map<String, String> returnOrder(@RequestBody Library library, @PathVariable int id){
        Map<String, String> hashMap = libraryService.updateOrder(library, id);
        return hashMap;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/library", method = RequestMethod.GET)
    public List<Book> bookSearch(@RequestParam String bookName){
        List<Book> bookList = null;
        if(bookName != null ){
            bookList = libraryService.findBookByName(bookName);
        }
        return bookList;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/library/{bookId}", method = RequestMethod.POST)
    public Map<String, String> updateBook(@RequestBody Library library, @PathVariable int bookId){
        Map<String, String> hashMap = new HashMap<>();
        if(bookId > 0){
            libraryService.updateLibrary(library, bookId);
            hashMap.put("message", "data updated successfully");
        }
        else{
            hashMap.put("message", "data updating failed");
        }
        return hashMap;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/library/{bookId}", method = RequestMethod.DELETE)
    public Map<String, String> deleteLibrary(@PathVariable int bookId){
        Map<String, String> hashMap = new HashMap<>();
        if(bookId > 0){
            hashMap = libraryService.deleteLibrary(bookId);
        }
        else{
            hashMap.put("message", "data deletion failed");
        }
        return hashMap;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/library/availability", method = RequestMethod.GET)
    public Map<String, Integer> checkBookAvailability(@RequestParam String bookName){
        Map<String, Integer> hashMap = new HashMap<>();
        int bookAvailable = 0;
        if(bookName != null){
            bookAvailable = libraryService.checkBookAvailability(bookName);
        }
        hashMap.put("bookAvailable",bookAvailable);
        return hashMap;
    }
}
