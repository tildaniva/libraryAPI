package com.example.LibraryAPI.repository;

import com.example.LibraryAPI.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findByIsbn(String isbn);
    List<Book> findByAuthorIgnoreCase(String author);
    boolean existsByIsbn(String isbn);
}
