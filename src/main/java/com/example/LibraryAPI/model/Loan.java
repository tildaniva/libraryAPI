package com.example.LibraryAPI.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "loans", uniqueConstraints = @UniqueConstraint(columnNames = "book_id"))
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "book_id", unique = true)
    private Book book;

    private LocalDate loanDate;
    private LocalDate returnDate;

    public Loan(){}
    public Loan(Book book){
        this.book = book;
        this.loanDate = LocalDate.now();
        this.returnDate = null;
    }

    public Long getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}

