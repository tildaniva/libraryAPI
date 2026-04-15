package com.example.LibraryAPI;

import com.example.LibraryAPI.dto.BookRequest;
import com.example.LibraryAPI.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.Map;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookIntegrationTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private BookRepository bookRepository;

    String url(){
        return "http://localhost:" + port + "/api/v1/books";
    }

    @BeforeEach
    void clear() {
        bookRepository.deleteAll();
    }

    @Test
    void postBook_returns201(){
        var req = new BookRequest();
        req.setTitle("The Great Gatsby");
        req.setAuthor("F. Scott Fitzgerald");
        req.setIsbn("9780743273565");
        req.setPublishedYear(1925);

        var res = restTemplate.postForEntity(url(), req, String.class);
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(bookRepository.count()).isEqualTo(1);

    }

    @Test
    void getBookById_returns200(){
     var req = new BookRequest();
     req.setTitle("Da Vinci Code");
     req.setAuthor("Dan Brown");
     req.setIsbn("9780743273565");
     req.setPublishedYear(2003);
     var created = restTemplate.postForEntity(url(), req, Map.class);

     Integer id = (Integer) created.getBody().get("id");
     var res = restTemplate.getForEntity(url() + "/" + id, String.class);
     assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
     assertThat(res.getBody()).contains("Da Vinci Code");
    }
}
