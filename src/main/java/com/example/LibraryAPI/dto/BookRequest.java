package com.example.LibraryAPI.dto;

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
