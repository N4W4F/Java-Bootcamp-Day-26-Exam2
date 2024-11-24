package com.example.librarymanagement.Controller;

import com.example.librarymanagement.ApiResponse.ApiResponse;
import com.example.librarymanagement.Model.User;
import com.example.librarymanagement.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getUsers() {
        if (userService.getUsers() == null)
            return ResponseEntity.status(400).body(new ApiResponse("There is no users yet"));

        return ResponseEntity.status(200).body(userService.getUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User has been added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable String id, @RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        if (userService.updateUser(id, user))
            return ResponseEntity.status(200).body(new ApiResponse("User with ID: " + id + " has bee updated"));

        return ResponseEntity.status(400).body(new ApiResponse("User with ID: " + id + " was not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable String id) {
        if (userService.deleteUser(id))
            return ResponseEntity.status(200).body(new ApiResponse("User with ID: " + id + " has been deleted"));

        return ResponseEntity.status(400).body(new ApiResponse("User with ID: " + id + " was not found"));
    }

    @GetMapping("/get-by-balance/{balance}")
    public ResponseEntity getByBalance(@PathVariable double balance) {
        if (userService.getByBalance(balance) == null)
            return ResponseEntity.status(400).body(new ApiResponse("There is no users have this amount of balance"));

        return ResponseEntity.status(200).body(userService.getByBalance(balance));
    }

    @GetMapping("/get-by-age/{age}")
    public ResponseEntity getByAge(@PathVariable int age) {
        if (userService.getByAge(age) == null)
            return ResponseEntity.status(400).body(new ApiResponse("There is no users in this age or above"));

        return ResponseEntity.status(200).body(userService.getByAge(age));
    }
}
