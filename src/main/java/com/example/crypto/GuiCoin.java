package com.example.crypto;

public class GuiCoin {
    private String symbol, name;

    public GuiCoin(String symbol, String name, Double price, Double lower, Double upper) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
        this.lower = lower;
        this.upper = upper;
    }

    private Double price, lower, upper;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getLower() {
        return lower;
    }

    public void setLower(Double lower) {
        this.lower = lower;
    }

    public Double getUpper() {
        return upper;
    }

    public void setUpper(Double upper) {
        this.upper = upper;
    }
}
