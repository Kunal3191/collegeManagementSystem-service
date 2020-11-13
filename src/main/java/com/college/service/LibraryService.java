package com.college.service;

import com.college.model.Library;
import com.college.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryService {

    @Autowired
    private LibraryRepository libraryRepository;

    public String saveBook(Library library){
        if(library != null){
            libraryRepository.save(library);
            return "data saved successfully";
        }
        return "data saving failed";
    }

    public Library findBookByName(String bookName){
        Library library = null;
        if(bookName != null){
            library = libraryRepository.findByBookName(bookName);
        }
        return library;
    }

    public Library findOrder(String bookName, String authorName){
        Library library = null;
        if(bookName != null){
            library = libraryRepository.findByBookNameAndAuthorName(bookName, authorName);
        }
        return library;
    }
}
