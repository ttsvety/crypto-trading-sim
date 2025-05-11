package com.example.crypto.model;

import java.sql.Timestamp;

public class Transaction {
    private int id;
    private int userId;
    private String type;
    private String symbol;
    private double quantity;
    private double price;
    private double total;
    private Timestamp timestamp;

    public Transaction(){}

    public Transaction(int id, int userId, String type, String symbol, double quantity, double price, double total, Timestamp timestamp){
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.symbol = symbol;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.timestamp = timestamp;
    }

    //GETTER and SETTER
    public int getId(){ return id; }
    public void setId(int id ){ this.id = id; }

    public int getUserId(){ return userId; }
    public void setUserId(int userId){ this.userId = userId; }

    public String getType(){ return type; }
    public void setType(String type){ this.type = type; }

    public String getSymbol(){ return symbol; }
    public void setSymbol(String symbol){ this.symbol = symbol; }

    public double getQuantity(){ return quantity; }
    public void setQuantity(double quantity){ this.quantity = quantity; }

    public double getPrice(){ return price; }
    public void setPrice(double price){ this.price = price; }

    public double getTotal(){ return total; }
    public void setTotal(double total){ this.total = total; }

    public Timestamp getTimestamp(){ return timestamp; }
    public void setTimestamp(Timestamp timestamp){ this.timestamp = timestamp; }

}