package com.example.LibraryAPI.exception;

public class BookAlreadyOnLoanException extends RuntimeException {
    public BookAlreadyOnLoanException() {
        super("Book is already on loan");
    }
}
