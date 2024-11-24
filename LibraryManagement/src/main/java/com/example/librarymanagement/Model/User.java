package com.example.librarymanagement.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    @NotEmpty(message = "User ID cannot be empty")
    private String id;

    @NotEmpty(message = "User Name cannot be empty")
    private String name;

    @NotNull(message = "User Age cannot be empty")
    private int age;

    @NotNull(message = "User Balance cannot be empty")
    @PositiveOrZero(message = "User Balance must be valid number")
    private double balance;

    @NotEmpty(message = "User Role cannot be empty")
    @Pattern(regexp = "^(Customer|Librarian)$", message = "User Role must be either Customer or Librarian")
    private String role;
}
