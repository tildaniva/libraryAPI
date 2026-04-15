package com.example.LibraryAPI.service;

import com.example.LibraryAPI.dto.LoanRequest;
import com.example.LibraryAPI.dto.LoanResponse;
import com.example.LibraryAPI.model.Book;
import com.example.LibraryAPI.model.Loan;
import com.example.LibraryAPI.repository.BookRepository;
import com.example.LibraryAPI.repository.LoanRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {
    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;

    public LoanService(LoanRepository loanRepository, BookRepository bookRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public LoanResponse create(LoanRequest request){
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new IllegalArgumentException("Book with id " + request.getBookId() + " not found"));

        if (loanRepository.existsByBookIdAndReturnDateIsNull(book.getId())){
            throw new IllegalArgumentException("Book is already on loan");
        }

        Loan saved = loanRepository.save(new Loan(book));
        return toDto(saved);
    }

    public List<LoanResponse> activeLoans(){
        return loanRepository.findByReturnDateIsNull()
                .stream()
                .map(this::toDto)
                .toList();
    }

    private LoanResponse toDto(Loan loan){
        return new LoanResponse(loan.getId(), loan.getBook().getId(), loan.getBook().getTitle(), loan.getLoanDate(), loan.getReturnDate());
    }
}
