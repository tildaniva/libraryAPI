package com.example.LibraryAPI.controller;

import com.example.LibraryAPI.dto.LoanRequest;
import com.example.LibraryAPI.dto.LoanResponse;
import com.example.LibraryAPI.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "Create a new loan")
    @ApiResponse(responseCode = "201", description = "Created")
    @ApiResponse(responseCode = "400", description = "Book is already on loan/ invalid input")
    @ApiResponse(responseCode = "404", description = "Book not found")
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
