package com.example.LibraryAPI.dto;

import jakarta.validation.constraints.NotNull;

public class LoanRequest {
    @NotNull
    private int bookId;

    public int getBookId(){
        return bookId;
    }

    public void setBookId(int bookId){
        this.bookId = bookId;
    }
}
