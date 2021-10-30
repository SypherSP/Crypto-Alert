import Requests.*;
import java.io.IOException;
import java.util.Objects;

public class Coin extends Thread{
    public static String BASE_URL="https://api1.binance.com/api/v3/ticker/price?symbol=";
    public String symbol, name;
    public Double price;

    public Coin(String symbol, String name){
        this.symbol=symbol;
        this.name=name;
    }

    public Double getPrice() {
        return price;
    }

    public String getCoinName(){
        return name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void run(){
        while (true){
            setPrice(downloadPrice()); //keep downloading price and updating the coin price
        }
    }

    //parsing response to get the price
    public Double priceFromResponse(String res){
        int len=res.length();
        return Double.parseDouble(res.substring(22+this.symbol.length(),len-2));
    }

    //downloading the price of the coin using Get class
    public Double downloadPrice(){
        Get req=new Get(BASE_URL+this.symbol);
        try{
            String res=req.response();
            if(res==null) return null; //checking if any exception occurred
            return priceFromResponse(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; //default
    }
}
