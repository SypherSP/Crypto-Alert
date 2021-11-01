package com.example.crypto;

import com.example.crypto.Requests.Get;

import java.io.IOException;



public class Coin extends Thread {
    public static String BASE_URL = "https://api1.binance.com/api/v3/ticker/price?symbol=";
    private String symbol, name;
    private Double price, lower, upper;

    public Coin(String symbol, String name) {
        this.symbol = symbol;
        this.name = name;
        setPrice(downloadPrice());
        upper = null;
        lower = null;
    }

    public Coin(String symbol, String name, double lower, double upper) {
        this.symbol = symbol;
        this.name = name;
        setPrice(downloadPrice());
        this.lower = lower;
        this.upper = upper;
    }

    public Double getPrice() {
        return price;
    }

    public String getCoinName() {
        return name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void run() {
        while (true) {
            try {
                sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setPrice(downloadPrice()); // keep downloading price and updating the coin price

            if (upper != null && lower != null)
                if (price >= upper)
                    upperBreached();
                else if (price <= lower)
                    lowerBreached();
        }
    }

    void upperBreached() {
        System.out.println("ALERT: " + name + " price exceeded " + upper);
    }

    void lowerBreached() {
        System.out.println("ALERT: " + name + " price fell below " + lower);
    }

    // parsing response to get the price
    public Double priceFromResponse(String res) {
        int len = res.length();
        return Double.parseDouble(res.substring(22 + this.symbol.length(), len - 2));
    }

    // downloading the price of the coin using Get class
    public Double downloadPrice() {
        Get req = new Get(BASE_URL + this.symbol);
        try {
            String res = req.response();
            if (res == null)
                return null; // checking if any exception occurred
            return priceFromResponse(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // default
    }
}