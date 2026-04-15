package com.example.LibraryAPI.controller;

import com.example.LibraryAPI.dto.BookRequest;
import com.example.LibraryAPI.dto.BookResponse;
import com.example.LibraryAPI.model.Book;
import com.example.LibraryAPI.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookControllerV1 {
    private final BookService bookService;

    public BookControllerV1(BookService bookService) {
        this.bookService = bookService;
    }

    @Operation(summary = "Create a new book")
    @ApiResponse(responseCode = "201", description = "Created")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @PostMapping
    public ResponseEntity<BookResponse> create(@Valid @RequestBody BookRequest dto){
        Book saved = bookService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(saved));
    }

    @Operation(summary = "Get all books")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping
    public List<BookResponse> findAll(){
        return bookService.findAll().stream().map(this::toResponse).toList();
    }

    @Operation(summary = "Get book by id")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "Book not found")
    @GetMapping("/{id}")
    public BookResponse findById(@PathVariable Long id){
        return toResponse(bookService.findById(id));
    }

    @GetMapping("/search")
    public List<BookResponse> findByAuthor(@RequestParam String author){
        return bookService.findByAuthor(author).stream().map(this::toResponse).toList();
    }

    private BookResponse toResponse(Book b){
        return new BookResponse(b.getId(), b.getTitle(), b.getAuthor(), b.getIsbn(), b.getPublishedYear());
    }
}
