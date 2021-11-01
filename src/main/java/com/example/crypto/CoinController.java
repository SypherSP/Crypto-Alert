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
import java.util.ResourceBundle;

public class CoinController implements Initializable {
    @FXML
    private TableColumn<GuiCoin, Double> price;

    @FXML
    private TableColumn<GuiCoin,Double> upper;

    @FXML
    private TableColumn<GuiCoin, Double> lower;

    @FXML
    private TableColumn<GuiCoin, String> name;

    @FXML
    private TableView<GuiCoin> table;

    //    dummy data
    ObservableList<GuiCoin> list = FXCollections.observableArrayList(
            new GuiCoin("bitcoin", "bitcoin", 23.0 ,23.0, 23.0 ),
            new GuiCoin("bitcoin", "Binance Coin", 23.0 ,23.0, 23.0 ),
            new GuiCoin("bitcoin", "Ethereum Coin", 23.0 ,23.0, 23.0 ),
            new GuiCoin("bitcoin", "Shib Inu", 23.0 ,23.0, 23.0 )
    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setCellValueFactory(new PropertyValueFactory<GuiCoin, String>("name"));
        price.setCellValueFactory(new PropertyValueFactory<GuiCoin, Double>("price"));
        upper.setCellValueFactory(new PropertyValueFactory<GuiCoin, Double>("upper"));
        lower.setCellValueFactory(new PropertyValueFactory<GuiCoin, Double>("lower"));

        table.setItems(list);
    }
    @FXML
    private void deleteRowFromTable(ActionEvent event){
        table.getItems().removeAll(table.getSelectionModel().getSelectedItem());
    }
}