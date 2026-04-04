package com.example.LibraryAPI.model;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books = new ArrayList<>();

    public Book addBook(Book book){
        for (Book b : books){
            if (b.getIsbn().equals(book.getIsbn())){
                throw new IllegalArgumentException("Book with ISBN " + book.getIsbn() + " already exists");
            }
        }
        books.add(book);
        return book;
    }

    public void removeBook(String isbn){
        Book book = findBook(isbn);
        books.remove(book);
    }

    public Book findBook(String isbn){
        for (Book book : books){
            if (book.getIsbn().equals(isbn)){
                return book;
            }
        }
        throw new IllegalArgumentException("Book not found " + isbn);
    }

    public List<Book> getAllBooks(){
        return books;
    }

    public Book getBookById (int id){
        return books.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }
}
