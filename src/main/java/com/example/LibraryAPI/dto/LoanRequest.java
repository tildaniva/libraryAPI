package com.example.LibraryAPI.dto;

import jakarta.validation.constraints.NotNull;

public class LoanRequest {
    @NotNull
    private Long bookId;

    public Long getBookId(){
        return bookId;
    }

    public void setBookId(Long bookId){
        this.bookId = bookId;
    }
}
