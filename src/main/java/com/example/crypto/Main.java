package com.example.crypto;

import java.util.*;

public class Main {
    static int INTERVAL=2; //seconds


    public static void prettyPrint(Coin coin){
        if(coin.getPrice()!=null) //before we hit the api, the value of coin price is null
            System.out.println(coin.getCoinName() + " - " + coin.getPrice());
    }

    public static void main(String[] args) {
        List<Coin> coinList= new ArrayList<>();
        coinList.add(new Coin("BNBBUSD","Binance Coin"));//initializing coin
        coinList.add(new Coin("BTCBUSD","Bitcoin"));//initializing coin
        coinList.add(new Coin("ETHBUSD","Ethereum Coin"));//initializing coin
        coinList.add(new Coin("SHIBBUSD","Shib Inu"));//initializing coin
        for(Coin coin:coinList){
            coin.start(); //starting all threads
        }

        //the following is just to print the coin.getPrice at constant intervals.
        //the thread work happens in run function of Coin class. There it continuously updates the price

        Timer t=new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                for(Coin coin:coinList){
                    prettyPrint(coin);
                }
            }
        },0,INTERVAL* 1000L);

    }
}