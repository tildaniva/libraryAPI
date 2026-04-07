package com.example.LibraryAPI.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Book request")
public class BookRequest {
    private String title;
    private String author;
    private String isbn;
    private int publicationYear;

    public String getTitle() {
        return title;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAuthor() {
        return author;
    }
}
