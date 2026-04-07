package com.example.LibraryAPI.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Book response for V2")
public class BookResponseV2 {
    private String title;
    private String author;
    private boolean isAvailable;

    public BookResponseV2(String title, String author, boolean isAvailable){
        this.title = title;
        this.author = author;
        this.isAvailable = isAvailable;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
}
