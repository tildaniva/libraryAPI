package com.example.LibraryAPI.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(int id) {
        super("Book with id " + id + " not found");
    }
}
