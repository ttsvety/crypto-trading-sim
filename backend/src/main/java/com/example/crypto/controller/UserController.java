package com.example.crypto.controller;

import com.example.crypto.model.User;
import com.example.crypto.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    
    public final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id){
        return userService.getUserById(id);
    }

    @PutMapping("/{id}/{newbalance}")
    public void updateBalance(@PathVariable int id, @PathVariable double newBalance){
        User user = userService.getUserById(id);
        if (user == null) {
            throw new IllegalArgumentException("User with this ID is not available");
        }
        if (newBalance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative.");
        }
        userService.updateBalance(id, newBalance);
    }

   @PutMapping("/{userId}/reset")
    public void resetBalance(@PathVariable int userId) {
        userService.resetBalance(userId);
}
}
