# LibraryAPI

A RESTful API for managing a library system built with Spring Boot. This application provides endpoints for managing books, authors, and book loans with OpenAPI documentation.

## Tech Stack

- **Java 21**
- **Spring Boot 3.3.5**
- **Spring Data JPA** - Data persistence
- **Spring Validation** - Request validation
- **H2 Database** - In-memory database
- **SpringDoc OpenAPI** - API documentation (Swagger UI)

## Project Structure
- **controller/**: REST controllers (hanterar HTTP requests, t.ex. Books, Authors, Loans)

- **dto/**: Data Transfer Objects (request/response mellan API och klient)

- **exception/**: Global felhantering (custom exceptions + @RestControllerAdvice)

- **model/**: Domänmodeller / entiteter (Book, Author, Loan)

- **repository/**: Databaslager (Spring Data JPA repositories)

- **service/**: Affärslogik (kopplar ihop controller och repository)

## API Endpoints

### Books API

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/v1/books` | Get all books |
| GET | `/api/v1/books/{id}` | Get book by ID |
| POST | `/api/v1/books` | Create a new book |
| GET | `/api/v1/books/search?author={name}` | Search books by author |

**Book Request Body:**
```json
{
  "title": "The Great Gatsby",
  "author": "F. Scott Fitzgerald",
  "isbn": "9780743273565",
  "publishedYear": 1925
}
```

**Authors API**

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/v1/authors/{id}` | Get author by ID|
| POST | `/api/v1/authors` | Create a new author |
| GET | `/api/v1/authors/{id}/books` | Get all books by author |

**Author Request Body:**

```json
{
  "name": "Jane Austen"
}
```

**Loans API**

| Method | Endpoint        | Description |
|--------|-----------------|-------------|
| GET | `/api/v1/loans` | Get all active loans|
| POST | `/api/v1/loans` | Create a new loan |

**Loans Request Body**

```json
{
"bookId": 1
}
```

**API Versioning**
- v1: Standard book responses with all fields 
- v2: Simplified book response with availability flag 
- `GET /api/v2/books` - Returns wrapped response with version info

## Database
The application uses an H2 in-memory database with the following configuration:
- URL: `jdbc:h2:mem:librarydb`
- Console: Available at /h2-console (enabled in development)

## Getting Started
Prerequisites
- Java 21 
- Maven

**Running the Application**

```
# Clone the repository
git clone <repository-url>

# Navigate to project directory
cd library-api

# Run with Maven
./mvnw spring-boot:run

# Or on Windows
mvnw.cmd spring-boot:run
```

Accessing the API
- Base URL: http://localhost:8080
- Swagger UI: http://localhost:8080/swagger-ui.html
- OpenAPI Docs: http://localhost:8080/v3/api-docs
- H2 Console: http://localhost:8080/h2-console

## Testing

Run the integration tests:

```
./mvnw test
```

The test suite includes:
- Book CRUD operations 
- Author management and book associations 
- Loan creation with concurrency testing (prevents double-loaning)

## Domain Model
- Book: Stores book information (title, author, ISBN, published year)
- Author: Manages author entities with associated books 
- Loan: Tracks book loans with loan date and return date (one-to-one with books)

## Features
- Bean Validation: Input validation using Jakarta Validation 
- Global Exception Handling: Consistent error responses 
- OpenAPI Documentation: Interactive API documentation via Swagger UI 
- Concurrency Safety: Loan creation is thread-safe to prevent duplicate loans 
- Unique Constraints: ISBN must be unique across books
