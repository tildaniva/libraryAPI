package com.example.LibraryAPI.controller;

import com.example.LibraryAPI.dto.BookResponseV2;
import com.example.LibraryAPI.dto.BookWrapperResponse;
import com.example.LibraryAPI.service.LibraryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2/books")
public class BookControllerV2 {
    private final LibraryService libraryService;

    public BookControllerV2(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping
    public BookWrapperResponse getAllBooksV2() {
        List<BookResponseV2> books = libraryService.getAllBooks()
                .stream()
                .map(b -> new BookResponseV2(
                        b.getTitle(),
                        b.getAuthor(),
                        true
                ))
                .collect(Collectors.toList());
        return new BookWrapperResponse(books, "v2");
    }
}
