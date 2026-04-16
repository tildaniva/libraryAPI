package com.example.LibraryAPI.controller;

import com.example.LibraryAPI.dto.AuthorDto;
import com.example.LibraryAPI.dto.BookResponse;
import com.example.LibraryAPI.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Operation(summary = "Create a new author")
    @ApiResponse(responseCode = "201", description = "Created")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @ApiResponse(responseCode = "404", description = "Author not found")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorDto createAuthor(@Valid @RequestBody AuthorDto authorDto) {
        return authorService.create(authorDto);
    }

    @GetMapping("/{id}")
    public AuthorDto findById(@PathVariable Long id){
        return authorService.findById(id);
    }

    @GetMapping("/{id}/books")
    public List<BookResponse> booksByAuthor(@PathVariable Long id){
        var a = authorService.findEntityById(id);
        return a.getBooks().stream()
                .map(b -> new BookResponse(b.getId(), b.getTitle(), b.getAuthor(), b.getIsbn(), b.getPublishedYear()))
                .toList();
    }
}
