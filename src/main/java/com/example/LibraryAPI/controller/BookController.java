package com.example.LibraryAPI.controller;

import com.example.LibraryAPI.dto.BookRequest;
import com.example.LibraryAPI.dto.BookResponse;
import com.example.LibraryAPI.model.Book;
import com.example.LibraryAPI.service.LibraryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final LibraryService libraryService;

    public BookController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    // POST /api/v1/books
    @Operation(summary = "Create a new book")
    @ApiResponse(responseCode = "201", description = "Successfully created book")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @PostMapping
    public ResponseEntity<BookResponse> createBook(@RequestBody BookRequest request) {

        // Validering
        if (request.getTitle() == null || request.getTitle().isBlank() ||
                request.getAuthor() == null || request.getAuthor().isBlank()) {

            return ResponseEntity.badRequest().build(); // 400
        }

        Book book = new Book(
                request.getTitle(),
                request.getAuthor(),
                request.getIsbn(),
                request.getPublicationYear()
        );

        Book saved = libraryService.addBook(book);

        BookResponse response = new BookResponse(
                saved.getId(),
                saved.getTitle(),
                saved.getAuthor(),
                saved.getIsbn(),
                saved.getPublicationYear()
        );

        return ResponseEntity.status(201).body(response);
    }

    // GET /api/v1/books
    @Operation(summary = "Get all books")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of books")
    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks() {

        List<BookResponse> response = libraryService.getAllBooks()
                .stream()
                .map(book -> new BookResponse(
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getIsbn(),
                        book.getPublicationYear()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    // GET /api/v1/books/{id}
    @Operation(summary = "Get book by ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved book")
    @ApiResponse(responseCode = "404", description = "Book not found")
    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable int id) {
        Book book = libraryService.getBookById(id);

        BookResponse response = new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getPublicationYear()
        );

        return ResponseEntity.ok(response);
    }

}
