package com.example.LibraryAPI.exception;

public class DuplicateIsbnException extends RuntimeException {
    public DuplicateIsbnException(String isbn) {
        super("Book with isbn " + isbn + " already exists");
    }
}
