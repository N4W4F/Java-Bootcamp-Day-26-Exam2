package com.example.librarymanagement.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
    @NotEmpty(message = "Book ID cannot be empty")
    private String id;

    @NotEmpty(message = "Book Name cannot be empty")
    private String name;

    @NotNull(message = "Book Number of Pages cannot be empty")
    private int number_of_pages;

    @NotNull(message = "Book Price cannot be emtpy")
    private double price;

    @NotEmpty(message = "Book Category cannot be empty")
    @Pattern(regexp = "^(Novel|Academic)$", message = "Book Category must be either Novel or Academic")
    private String category;

    @NotNull(message = "Book's isAvailable cannot be empty")
    private boolean isAvailable;
}
