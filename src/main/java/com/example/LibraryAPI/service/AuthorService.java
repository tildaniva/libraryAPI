package com.example.LibraryAPI.service;

import com.example.LibraryAPI.dto.AuthorDto;
import com.example.LibraryAPI.model.Author;
import com.example.LibraryAPI.repository.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public AuthorDto create(AuthorDto dto){
        Author a = new Author(dto.getName());
        Author savedAuthor = authorRepository.save(a);
        return new AuthorDto(savedAuthor.getId(), savedAuthor.getName(), 0);
    }

    public Author findEntityById(int id) {
        return authorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Author with id " + id + " not found"));
    }

    public AuthorDto findById(int id) {
        Author author = findEntityById(id);
        return new AuthorDto(author.getId(), author.getName(), author.getBooks().size());
    }
}
