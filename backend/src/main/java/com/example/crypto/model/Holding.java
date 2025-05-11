package com.example.crypto.model;

public class Holding {
    private int id;
    private int userId;
    private String symbol;
    private double amount;

    public Holding(){}

    public Holding(int id, int userId, String symbol, double amount){
        this.id = id;
        this.userId = userId;
        this.symbol = symbol;
        this.amount = amount;
    }

    //GETTER and SETTER
    public int getId(){ return id; }
    public void setId(int id){ this.id = id;}

    public int getUserId(){ return userId; }
    public void setUserId(int userId){ this.userId = userId; }

    public String getSymbol(){ return symbol; }
    public void setSymbol(String symbol){ this.symbol = symbol; }

    public double getAmount(){ return amount; }
    public void setAmount(double amount){ this.amount = amount; }

}
