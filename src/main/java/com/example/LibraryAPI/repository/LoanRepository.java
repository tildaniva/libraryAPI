package com.example.LibraryAPI.repository;

import com.example.LibraryAPI.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    boolean existsByBookIdAndReturnDateIsNull(Long bookId);
    List<Loan> findByReturnDateIsNull();
}
