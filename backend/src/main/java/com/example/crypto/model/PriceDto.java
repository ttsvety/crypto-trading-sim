package com.example.crypto.model;

public class PriceDto {
    private String symbol;
    private double price;

    public PriceDto(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }
}

