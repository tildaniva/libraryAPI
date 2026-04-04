package com.example.LibraryAPI.dto;

public class BookResponse {
    private int id;
    private String title;
    private String author;
    private String isbn;
    private int publicationYear;

    public BookResponse(int id, String title, String author, String isbn, int publicationYear){
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
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
}
