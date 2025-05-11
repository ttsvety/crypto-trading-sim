package com.example.crypto.controller;

import com.example.crypto.model.Transaction;
import com.example.crypto.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

   @GetMapping("/user/{userId}")
public List<Transaction> getTransactionsByUserId(@PathVariable int userId) {
    List<Transaction> transactions = transactionService.getTransactionsByUserId(userId);
    System.out.println("Transactions for user " + userId + ": " + transactions.size());
    return transactions;
}

@PostMapping
public ResponseEntity<String> addTransaction(@RequestBody Transaction transaction) {
    System.out.println("Added transaction: " + transaction);
    try {
        transactionService.addTransaction(transaction);
        return ResponseEntity.ok("Transaction successful");
    } catch (IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    } catch (Exception e) {
        return ResponseEntity.status(500).body("Internal server error");
    }
}


}
