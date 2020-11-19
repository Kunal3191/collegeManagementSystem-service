package com.college.service;

import com.college.model.Book;
import com.college.model.Library;
import com.college.repository.BookRepository;
import com.college.repository.LibraryRepository;
import org.hibernate.sql.AliasGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibraryService {

    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private BookRepository bookRepository;

    public String saveBook(Book book){
        if(book != null){
            bookRepository.save(book);
            return "data saved successfully";
        }
        return "data saving failed";
    }

    public List<Book> findBookByName(String bookName){
        List<Book> bookList = null;
        if(bookName != null){
            bookList = bookRepository.findAll()
                   .stream().filter(book -> book.getBookName().toLowerCase()
                           .contains(bookName.toLowerCase())).collect(Collectors.toList());

        }
        return bookList;
    }

    public Library findOrder(String bookName, String authorName){
        Library library = null;
        if(bookName != null){
           // library = libraryRepository.findByBookNameAndAuthorName(bookName, authorName);
        }
        return library;
    }

    public Map<String, String> updateLibrary(Library library, int bookId){
        Book book = null;
        Map<String, String> hashMap = new HashMap<>();
        Optional<Book> bookById = bookRepository.findById(bookId);
        if(bookById.isPresent()){
            book = bookById.get();
            library.setBook(book);
            Library savedLibrary = libraryRepository.save(library);
            hashMap.put("message", "data saved successfully");
        }
        else
        {
            hashMap.put("message", "data updating failed");
        }
        return hashMap;
    }
    public List<Library> getBookByStatus(String bookName){
        List<Library> libraryList = null;
        Book book = bookRepository.findByBookName(bookName);
        if(book != null){
            int bookId = book.getBookId();
            libraryList = libraryRepository.findAll().stream()
                    .filter(library -> library.getBook().getBookId() == bookId)
                    .filter(library -> library.getStatus().equalsIgnoreCase("booked"))
                    .collect(Collectors.toList());
        }

        return libraryList;
    }

    public Map<String, String> updateOrder(Library library, int id){
        Map<String, String> hashMap = new HashMap<>();
        Optional<Library> optionalLibrary =  libraryRepository.findById(id);
        if(optionalLibrary.isPresent()){
            Library existingLibrary = optionalLibrary.get();
            existingLibrary.setStatus("returned");
            existingLibrary.setLastReturnDate(library.getLastReturnDate());
            libraryRepository.save(existingLibrary);
            hashMap.put("message", "data saved successfully");
        }
        else{
            hashMap.put("message", "data saving failed");
        }
        return hashMap;
    }

    public Map<String, String> deleteLibrary(int bookId){
        Book book = null;
        Map<String, String> hashMap = new HashMap<>();
        Optional<Book> findById = bookRepository.findById(bookId);
        if(findById.isPresent()){
            book = findById.get();
            book.getLibraries().stream()
                    .filter(library -> library.getBook().getBookId() == bookId)
                    .forEach(library -> libraryRepository.delete(library));
            bookRepository.delete(book);
            hashMap.put("message", "data deleted successfully");
        }

        return hashMap;
    }

    public int checkBookAvailability(String bookName){
        long bookedQuantity = 0L;
        int bookAvailable = 0;
        Book book = bookRepository.findByBookName(bookName);
        if(book != null){
            int bookId = book.getBookId();
            int bookQuantity =book.getQuantity();
            bookedQuantity = libraryRepository.findAll().stream().filter(library -> library.getBook().getBookId() == book.getBookId())
                    .filter(library -> library.getStatus().equalsIgnoreCase("booked")).count();
            int bookedQuantityIntegerValue = (int)bookedQuantity;
            if(bookedQuantityIntegerValue < bookQuantity){
                bookAvailable = Math.subtractExact(bookQuantity, bookedQuantityIntegerValue);
            }
        }

        return bookAvailable;
    }
}
