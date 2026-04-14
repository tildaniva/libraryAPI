package com.example.LibraryAPI.dto;

import jakarta.validation.constraints.NotBlank;

public class AuthorDto {
    private int id;
    @NotBlank
    private String name;
    private int numberOfBooks;

    public AuthorDto() {}

    public AuthorDto(int id, String name, int numberOfBooks) {
        this.id = id;
        this.name = name;
        this.numberOfBooks = numberOfBooks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfBooks() {
        return numberOfBooks;
    }

    public void setNumberOfBooks(int numberOfBooks) {
        this.numberOfBooks = numberOfBooks;
    }
}
