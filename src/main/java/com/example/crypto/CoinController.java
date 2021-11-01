package com.example.crypto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CoinController implements Initializable {
//    private ScheduledExecutorService executorService;
    @FXML
    private TableColumn<GuiCoin, Double> price;
    @FXML
    private TableColumn<GuiCoin, String> name;
    @FXML
    private TableView<GuiCoin> table;


    ObservableList<GuiCoin> myList = populateData();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setCellValueFactory(new PropertyValueFactory<GuiCoin, String>("name"));
        price.setCellValueFactory(new PropertyValueFactory<GuiCoin, Double>("price"));
        table.setItems(myList);
    }
    @FXML
    private void deleteRowFromTable(ActionEvent event){
        table.getItems().removeAll(table.getSelectionModel().getSelectedItem());
    }

    private ObservableList<GuiCoin> populateData(){
        List<Coin> coinList= new ArrayList<>(4);
        ObservableList<GuiCoin> myList = FXCollections.observableArrayList();

        //initializing coins
        //set lower and upper values from form when adding a coin
        coinList.add(new Coin("BNBBUSD","Binance Coin"));
        coinList.add(new Coin("BTCBUSD","Bitcoin"));
        coinList.add(new Coin("ETHBUSD","Ethereum Coin"));
        coinList.add(new Coin("SHIBBUSD","Shib Inu"));

        for(Coin coin:coinList){
            coin.start(); //starting all threads
        }
        for(Coin coin:coinList) {
            if(coin.getPrice()!=null)
            myList.add(new GuiCoin(coin.getName(), coin.getPrice()));
        }
        Timer t=new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                for(int i=0;i<4;i++) {
                    if(coinList.get(i).getPrice()!=null)
                    myList.set(i, new GuiCoin(coinList.get(i).getCoinName(), coinList.get(i).getPrice()));
                }
            }
        },0,2* 1000L);

        return myList;
    }
}