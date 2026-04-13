package com.example.LibraryAPI.exception;

public class DublicateIsbnException extends RuntimeException {
    public DublicateIsbnException(String isbn) {
        super("Book with isbn " + isbn + " already exists");
    }
}
