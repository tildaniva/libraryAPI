package com.example.LibraryAPI;

import com.example.LibraryAPI.dto.AuthorDto;
import com.example.LibraryAPI.dto.BookRequest;
import com.example.LibraryAPI.dto.LoanRequest;
import com.example.LibraryAPI.repository.AuthorRepository;
import com.example.LibraryAPI.repository.BookRepository;
import com.example.LibraryAPI.repository.LoanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.concurrent.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthorLoanIntegrationTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private AuthorRepository authorRepository;

    String base(){
        return "http://localhost:" + port;
    }

    @BeforeEach
    void clear() {
        loanRepository.deleteAll();
        bookRepository.deleteAll();
        authorRepository.deleteAll();
    }

    @Test
    void createAuthorAndBook_thenGetAuthorBooks() {
        var a = new AuthorDto();
        a.setName("Jane Austen");
        var createdAuthor = restTemplate.postForEntity(base() + "/api/v1/authors", a, Map.class);
        int authorId = ((int) createdAuthor.getBody().get("id"));

        var b = new BookRequest();
        b.setTitle("Pride and Prejudice");
        b.setAuthor("Jane Austen");
        b.setIsbn("9780743273565");
        b.setPublishedYear(1813);
        restTemplate.postForEntity(base() + "/api/v1/books", b, Map.class);

        var res = restTemplate.getForEntity(base() + "/api/v1/authors/" + authorId + "/books", String.class);
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void loanSameBookTwice_secondIs400(){
        var b = new BookRequest();
        b.setTitle("Book");
        b.setAuthor("A");
        b.setIsbn("123");
        b.setPublishedYear(2023);
       var createdBook = restTemplate.postForEntity(base() + "/api/v1/books", b, Map.class);
       int bookId = ((int) createdBook.getBody().get("id"));

       var loanReq = new LoanRequest();
       loanReq.setBookId(bookId);

       var first = restTemplate.postForEntity(base() + "/api/v1/loans", loanReq, String.class);
       var second = restTemplate.postForEntity(base() + "/api/v1/loans", loanReq, String.class);

       assertThat(first.getStatusCode()).isEqualTo(HttpStatus.CREATED);
       assertThat(second.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }

    @Test
    void concurrentLoan_onlyOneCreated() throws Exception{
        var b = new BookRequest();
        b.setTitle("Concurrent");
        b.setAuthor("B");
        b.setIsbn("456");
        b.setPublishedYear(2024);
        var postBookResponse = restTemplate.postForEntity(base() + "/api/v1/books", b, Map.class);
        int bookId = ((int) postBookResponse.getBody().get("id"));

        ExecutorService executor = Executors.newFixedThreadPool(2);
        CountDownLatch latch = new CountDownLatch(1);

        Callable<Integer> task = () -> {
            latch.await();
            var req = new LoanRequest();
            req.setBookId(bookId);
            return restTemplate.postForEntity(base() + "/api/v1/loans", req, String.class).getStatusCodeValue();
        };

        Future<Integer> f1 = executor.submit(task);
        Future<Integer> f2 = executor.submit(task);
        latch.countDown();

        int s1 = f1.get();
        int s2 = f2.get();

        assertThat((s1 == 201 && s2 == 400) || (s1 == 400 && s2 == 201)).isTrue();
        assertThat(loanRepository.count()).isEqualTo(1);
        executor.shutdown();


    }
}
