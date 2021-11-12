package com.example.crypto;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

import java.net.URL;
import java.util.*;

public class CoinController implements Initializable {
    //    configure the table
    @FXML
    private TableColumn<Coin, Double> price;
    @FXML
    private TableColumn<Coin, String> name;
    @FXML
    private TableColumn<Coin, Double> lower;
    @FXML
    private TableColumn<Coin, Double> upper;
    @FXML
    private TableView<Coin> table;

    //    these instance variables are used to create new coins
    @FXML
    private TextField symbolTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField lowerTextField;
    @FXML
    private TextField upperTextField;

    ObservableList<Coin> myList = populateData();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setCellValueFactory(new PropertyValueFactory<Coin, String>("CoinName"));
        price.setCellValueFactory(new PropertyValueFactory<Coin, Double>("Price"));
        lower.setCellValueFactory(new PropertyValueFactory<Coin, Double>("Lower"));
        upper.setCellValueFactory(new PropertyValueFactory<Coin, Double>("Upper"));
        table.setItems(myList);
    }

    @FXML
    private void deleteRowFromTable(ActionEvent event) {
        myList.remove(table.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void refresh(ActionEvent event) {
        for (int i = 0; i < myList.size(); i++) {
            Coin temp = new Coin(myList.get(i).getSymbol(), myList.get(i).getCoinName(),
                    myList.get(i).getLower(), myList.get(i).getUpper());
            temp.start();
            if(temp.getPrice()>temp.getUpper()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Upper limit breached");
                a.setContentText(temp.getCoinName() + " price exceeded " + temp.getUpper());
                a.show();
            }
            else if(temp.getPrice()<temp.getLower()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Lower limit breached");
                a.setContentText(temp.getCoinName() + " price fell below " + temp.getLower());
                a.show();
            }
            myList.set(i, temp);
        }
    }

    @FXML
    private void addToList() {
         if(symbolTextField.getText().trim().isEmpty() || nameTextField.getText().trim().isEmpty() || lowerTextField.getText().trim().isEmpty() || upperTextField.getText().trim().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setContentText("One of the input fields in empty!");
            a.show();
            return;
        }
        Coin coinFromUser = new Coin(symbolTextField.getText(), nameTextField.getText(),
                Double.parseDouble(lowerTextField.getText()), Double.parseDouble(upperTextField.getText()));
        coinFromUser.start();
        for (int i = 0; i < myList.size(); i++) {
            if (Objects.equals(myList.get(i).getSymbol(), symbolTextField.getText())) {
                myList.set(i,coinFromUser);
                return;
            }
        }
        myList.add(coinFromUser);
    }
    
     private void refreshAuto() {
        for (int i = 0; i < myList.size(); i++) {
            Coin temp = new Coin(myList.get(i).getSymbol(), myList.get(i).getCoinName(),
                    myList.get(i).getLower(), myList.get(i).getUpper());
            temp.start();
            if(temp.getPrice()>temp.getUpper()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Upper limit breached");
                a.setContentText(temp.getCoinName() + " price exceeded " + temp.getUpper());
                a.show();
            }
            else if(temp.getPrice()<temp.getLower()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Lower limit breached");
                a.setContentText(temp.getCoinName() + " price fell below " + temp.getLower());
                a.show();
            }
            myList.set(i, temp);
        }
    }
    
    private ObservableList<Coin> populateData() {
        ObservableList<Coin> myList = FXCollections.observableArrayList();

        //initializing coins
        //set lower and upper values from form when adding a coin
        myList.add(new Coin("BNBBUSD", "Binance Coin"));
        myList.add(new Coin("BTCBUSD", "Bitcoin"));
        myList.add(new Coin("ETHBUSD", "Ethereum Coin"));
//        myList.add(new Coin("SHIBBUSD","Shib Inu"));

        for (Coin coin : myList) {
            coin.start(); //starting all threads
        }
        
//         for auto updating values 
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(4), ev -> {
            refreshAuto();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        
        return myList;
    }
}
