package com.example.LibraryAPI.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Book response")
public class BookResponse {
    private int id;
    private String title;
    private String author;
    private String isbn;
    private int publishedYear;

    public BookResponse(int id, String title, String author, String isbn, int publishedYear){
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publishedYear = publishedYear;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getPublishedYear() {
        return publishedYear;
    }
}
