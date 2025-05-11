package com.example.crypto.controller;

import com.example.crypto.model.Holding;
import com.example.crypto.service.HoldingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/holdings")
public class HoldingController {
    
    private final HoldingService holdingService;

    public HoldingController(HoldingService holdingService){
        this.holdingService = holdingService;
    }

    @GetMapping("/user/{userId}")
    public List<Holding> getHoldingsByUserId(@PathVariable int userId){
        return holdingService.getHoldingsByUserId(userId);  // Връща списък с притежания на потребителя
    }

    @PutMapping("/{userId}/{symbol}")
    public void updateHolding(@PathVariable int userId, @PathVariable String symbol, @RequestBody Holding holding){
        if (holding.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount should be positive.");
        }
        if (symbol == null || symbol.isEmpty()) {
            throw new IllegalArgumentException("Symbol cannot be empty");
        }
        holding.setUserId(userId);
        holding.setSymbol(symbol);
        holdingService.updateHolding(holding);
    }

    @PostMapping
    public void addHolding(@RequestBody Holding holding) {
        if (holding.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount should be positive.");
        }
        if (holding.getSymbol() == null || holding.getSymbol().isEmpty()) {
            throw new IllegalArgumentException("Symbol cannot be empty.");
        }
        holdingService.addHolding(holding);
    }

    @DeleteMapping("/user/{userIid}/symbol/{symbol}")
    public void deleteHolding(@PathVariable int userId, @PathVariable String symbol) {
        if (symbol == null || symbol.isEmpty()) {
            throw new IllegalArgumentException("Symbol cannot be empty");
        }
        holdingService.deleteHolding(userId, symbol);
    }
}
