package com.example.librarymanagement.Controller;

import com.example.librarymanagement.ApiResponse.ApiResponse;
import com.example.librarymanagement.Model.Book;
import com.example.librarymanagement.Service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;

    @GetMapping("/get")
    public ResponseEntity getBooks() {
        if (bookService.getBooks() == null)
            return ResponseEntity.status(400).body(new ApiResponse("We don't have books yet"));

        return ResponseEntity.status(200).body(bookService.getBooks());
    }

    @PostMapping("/add")
    public ResponseEntity addBook(@RequestBody @Valid Book book, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        bookService.addBook(book);
        return ResponseEntity.status(200).body(new ApiResponse("Book has been added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateBook(@PathVariable String id, @RequestBody @Valid Book book, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        if (bookService.updateBook(id, book))
            return ResponseEntity.status(200).body(new ApiResponse("Book with ID: " + id + " has been updated"));

        return ResponseEntity.status(400).body(new ApiResponse("Book with ID: " + id + " was not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBook(@PathVariable String id) {
        if (bookService.deleteBook(id))
            return ResponseEntity.status(200).body(new ApiResponse("Book with ID: " + id + " has been deleted"));

        return ResponseEntity.status(400).body(new ApiResponse("Book with ID: " + id + " was not found"));
    }

    @GetMapping("/get-by-name/{name}")
    public ResponseEntity getBookByName(@PathVariable String name) {
        if (bookService.getBookByName(name) == null)
            return ResponseEntity.status(400).body(new ApiResponse("There is no book with this name"));

        return ResponseEntity.status(200).body(bookService.getBookByName(name));
    }

    @GetMapping("/get-by-category/{category}")
    public ResponseEntity getBookByCategory(@PathVariable String category) {
        if (bookService.getBookByCategory(category) == null)
            return ResponseEntity.status(400).body(new ApiResponse("There is no books in this category"));

        return ResponseEntity.status(200).body(bookService.getBookByCategory(category));
    }

    @GetMapping("/get-by-pages/{pages}")
    public ResponseEntity getBookByPages(@PathVariable int pages) {
        if (bookService.getBookByPages(pages) == null)
            return ResponseEntity.status(400).body(new ApiResponse("There is no books with this number of pages"));

        return ResponseEntity.status(200).body(bookService.getBookByPages(pages));
    }

    @PutMapping("/change-status/{id}/{role}")
    public ResponseEntity changeStatus(@PathVariable String id, @PathVariable String role) {
        if (bookService.changeStatus(id, role).equalsIgnoreCase("yes"))
            return ResponseEntity.status(200).body(new ApiResponse("Book Status with ID: " + id + " has been changed"));

        else if (bookService.changeStatus(id, role).equalsIgnoreCase("no"))
            return ResponseEntity.status(400).body(new ApiResponse("Book with ID: " + id + " was not found"));

        else if (bookService.changeStatus(id, role).equalsIgnoreCase("customer"))
            return ResponseEntity.status(400).body(new ApiResponse("You don't have access to change books status"));

        return ResponseEntity.status(400).body(new ApiResponse("Role is not valid"));
    }
}
