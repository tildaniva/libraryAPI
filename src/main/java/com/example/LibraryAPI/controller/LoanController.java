package com.example.LibraryAPI.controller;

import com.example.LibraryAPI.dto.LoanRequest;
import com.example.LibraryAPI.dto.LoanResponse;
import com.example.LibraryAPI.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loans")
public class LoanController {
    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LoanResponse create(@Valid @RequestBody LoanRequest request) {
        return loanService.create(request);
    }

    @GetMapping
    public List<LoanResponse> activeLoans() {
        return loanService.activeLoans();
    }
}
