package com.college.repository;

import com.college.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Integer> {
    Library findByBookName(String bookName);
    Library findByBookNameAndAuthorName(String bookName, String authorName);
}
