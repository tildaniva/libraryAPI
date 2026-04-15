package com.example.LibraryAPI.controller;

import com.example.LibraryAPI.dto.AuthorDto;
import com.example.LibraryAPI.dto.BookResponse;
import com.example.LibraryAPI.service.AuthorService;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorDto createAuthor(@Valid @RequestBody AuthorDto authorDto) {
        return authorService.create(authorDto);
    }

    @GetMapping("/{id}")
    public AuthorDto findById(@PathVariable int id){
        return authorService.findById(id);
    }

    @GetMapping("/{id}/books")
    public List<BookResponse> booksByAuthor(@PathVariable int id){
        var a = authorService.findEntityById(id);
        return a.getBooks().stream()
                .map(b -> new BookResponse(b.getId(), b.getTitle(), b.getAuthor(), b.getIsbn(), b.getPublishedYear()))
                .toList();
    }
}
