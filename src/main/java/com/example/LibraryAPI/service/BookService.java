package com.example.LibraryAPI.service;

import com.example.LibraryAPI.dto.BookRequest;
import com.example.LibraryAPI.exception.BookNotFoundException;
import com.example.LibraryAPI.exception.DublicateIsbnException;
import com.example.LibraryAPI.model.Book;
import com.example.LibraryAPI.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book create(BookRequest dto) {
        if(dto.getIsbn() != null && !dto.getIsbn().isBlank() && bookRepository.existsByIsbn(dto.getIsbn())){
            throw new DublicateIsbnException(dto.getIsbn());
        }
        Book book = new Book(dto.getTitle(), dto.getAuthor(), dto.getIsbn(), dto.getPublicationYear());
        return bookRepository.save(book);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(int id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    public Book findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn).orElseThrow(() -> new IllegalArgumentException("Book with isbn " + isbn + " not found"));
    }

    public List<Book> findByAuthor(String author) {
        return bookRepository.findByAuthorIgnoreCase(author);
    }
}
