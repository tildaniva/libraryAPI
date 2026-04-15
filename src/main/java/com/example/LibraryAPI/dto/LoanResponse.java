package com.example.LibraryAPI.dto;

import java.time.LocalDate;

public class LoanResponse {
    private int id;
    private int bookId;
    private String bookTitle;
    private LocalDate loanDate;
    private LocalDate returnDate;

    public LoanResponse(int id, int bookId, String bookTitle, LocalDate loanDate, LocalDate returnDate){
        this.id = id;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

    public int getId() {
        return id;
    }

    public int getBookId() {
        return bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }
}
