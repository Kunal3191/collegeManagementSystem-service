package com.college.repository;

import com.college.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Integer> {
    List<Library> findByStatus(String status);
    //Library findByBookNameAndAuthorName(String bookName, String authorName);
    Library findByFirstNameAndLastName(String firstName, String lastName);
}
