package com.example.crypto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;

import java.net.URL;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CoinController implements Initializable {
//    configure the table
    @FXML private TableColumn<Coin, Double> price;
    @FXML private TableColumn<Coin, String> name;
    @FXML private TableView<Coin> table;

//    these instance variables are used to create new coins
    @FXML private TextField symbolTextField;
    @FXML private TextField nameTextField;

    ObservableList<Coin> myList = populateData();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setCellValueFactory(new PropertyValueFactory<Coin, String>("CoinName"));
        price.setCellValueFactory(new PropertyValueFactory<Coin, Double>("price"));
        table.setItems(myList);
    }

    @FXML
    private void deleteRowFromTable(ActionEvent event){
        myList.remove(table.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void refresh(ActionEvent event) {
        for(int i=0;i<myList.size();i++) {
            Coin temp = new Coin(myList.get(i).getSymbol(), myList.get(i).getCoinName());
            temp.start();
            myList.set(i,temp);
        }
        table.setItems(myList);
    }

    @FXML
    private void addToList() {
        Coin coinFromUser = new Coin(symbolTextField.getText(), nameTextField.getText());
        coinFromUser.start();
        myList.add(coinFromUser);
    }

    private ObservableList<Coin> populateData(){
        ObservableList<Coin> myList = FXCollections.observableArrayList();

        //initializing coins
        //set lower and upper values from form when adding a coin
        myList.add(new Coin("BNBBUSD","Binance Coin"));
        myList.add(new Coin("BTCBUSD","Bitcoin"));
        myList.add(new Coin("ETHBUSD","Ethereum Coin"));
//        myList.add(new Coin("SHIBBUSD","Shib Inu"));

        for(Coin coin:myList){
            coin.start(); //starting all threads
        }
        return myList;
    }
}
