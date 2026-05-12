package com.example.LibraryAPI.controller;

import com.example.LibraryAPI.dto.BookResponseV2;
import com.example.LibraryAPI.dto.BookV2Wrapper;
import com.example.LibraryAPI.service.BookService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/books")
public class BookControllerV2 {
  private final BookService bookService;

  public BookControllerV2(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping
  public BookV2Wrapper findAllV2(Pageable pageable) {
    var data = bookService.findAll(pageable)
        .map(b -> new BookResponseV2(b.getTitle(), b.getAuthor(), true))
        .getContent();
        
    return new BookV2Wrapper(data, "v2");
  }
}
