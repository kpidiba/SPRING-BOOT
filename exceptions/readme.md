# Spring Boot REST API Exception Handling Guide

### RESSOURCES

- https://namekjoel.medium.com/gérez-les-exceptions-de-manière-cohérente-et-centralisée-dans-spring-mvc-avec-controlleradvice-98c0f9078929

- [Apprendre Gestion des Exceptions | API RESTful](https://codefinity.com/fr/courses/v2/87dc501e-89a6-4a4b-afd4-8b38c46a80c7/4c0caf64-3c65-47b4-8075-31958fa1f42a/247e4935-736f-4601-bc7d-7a094f234d30)

- 

![](https://miro.medium.com/v2/resize:fit:434/format:webp/0*LEK1Kzs_JjD08ZaS.png)

## Overview

This repository demonstrates **best practices for exception handling** in a Spring Boot REST API. Proper exception management is crucial for:

- Providing **meaningful error responses** to API clients

- **Standardizing error formats** across your application

- **Centralizing error handling** logic

- Differentiating between **error types** (validation, not found, bad requests, etc.)

## Key Features

- ✅ Global exception handling with `@ControllerAdvice`

- ✅ Custom exception classes for specific error scenarios

- ✅ Consistent error response format

- ✅ Proper HTTP status code mapping

- ✅ Example implementations for common exception cases

## Implementation Details

### Error Response Structure

All error responses follow this standardized format:

```java
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ApiResponse<T> {
    private int status;          // HTTP status code
    private String message;     // Error description
    private T data;            // Optional error details
    private LocalDateTime timestamp;  // When error occurred
}
```

### Custom Exceptions

We've created specific exception types for different error scenarios:

```java

@ExceptionHandler(BadRequestException.class)
public class ExcelFileException extends RuntimeException{

    public ExcelFileException(String message){
        super(message);
    }

    public ExcelFileException(String message,Throwable cause){
        super(message,cause);
    }
}

// When a book isn't found
@ExceptionHandler(BookNotFoundException.class)
public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Integer bookId) {
        super("Book not found with ID: " + bookId);
    }
}


```

### Global Exception Handler

The `GlobalExceptionHandler` class centralizes all exception handling:

```java
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleBookNotFound(BookNotFoundException ex) {
        ApiResponse<String> response = new ApiResponse<>(
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage(),
            null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
```

### Recommended Package Structure

```json
src/main/java/
└── com/
    └── yourcompany/
        └── yourapp/
            ├── exception/
            │   ├── handler/
            │   │   └── GlobalExceptionHandler.java
            │   ├── custom/
            │   │   ├── BookNotFoundException.java
            │   │   ├── BadRequestException.java
            │   │   └── (other custom exceptions)
            │   └── model/
            │       └── ErrorEntity.java
            └── payload/
                └── ApiResponse.java
```

### Service Layer Implementation

The service layer throws appropriate exceptions:

```java
@Service
public class BookServiceImp implements BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book saveBook(Book book) {
        try {
            return bookRepository.save(book);
        } catch (Exception ex) {
            throw new BadRequestException(ex.getMessage());
        }
    }

    public Optional<Book> getBookById(Integer id) {
        return Optional.ofNullable(bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id)));
    }

    public void deleteBook(Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        bookRepository.deleteById(book.getId());
    }
}
```

## Flow Diagram

![](https://miro.medium.com/v2/resize:fit:720/format:webp/1*7qPHjIrg_wQJwaCp2tXO3w.png)

1. Client sends request to API endpoint

2. If error occurs, service throws specific exception

3. `@ControllerAdvice` catches the exception

4. Appropriate handler method creates standardized error response

5. Client receives consistent error format

## Getting Started

1. Clone this repository

2. Import as Maven project in your IDE

3. Run the Spring Boot application

4. Test endpoints with Postman or curl

## Example API Endpoints

```java
@RestController
@RequestMapping("api/v1/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public Book saveBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @GetMapping("/{bookId}")
    public Optional<Book> getBookById(@PathVariable Integer bookId) {
        return bookService.getBookById(bookId);
    }

    @DeleteMapping("/{bookId}")
    public void deleteBook(@PathVariable Integer bookId) {
        bookService.deleteBook(bookId);
    }
}
```

## Best Practices Demonstrated

- **Separation of concerns**: Error handling logic is centralized

- **Specific exceptions**: Different error types have dedicated exception classes

- **Consistent responses**: All errors follow the same response structure

- **Proper HTTP codes**: Each error returns the most appropriate status code

- **Helpful messages**: Error messages provide actionable information

## Contributing

Contributions are welcome! Please open an issue or submit a pull request for any improvements.
