package com.example.LibraryAPI.service;

import com.example.LibraryAPI.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {
    private final Library library = new Library();

    public Book addBook(Book book) {
        return library.addBook(book);
    }

    public List<Book> getAllBooks() {
        return library.getAllBooks();
    }

    public Book getBookById(int id) {
        return library.getBookById(id);
    }

    public void removeBook(String isbn) {
        library.removeBook(isbn);
    }

    public Book findBook(String isbn) {
        return library.findBook(isbn);
    }
}
