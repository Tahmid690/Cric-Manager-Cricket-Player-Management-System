package com.example.cric_manager.Controllers;

import com.example.cric_manager.Base.Client;
import com.example.cric_manager.Base.SocketWrapper;
import com.example.cric_manager.Core_1.CricketPlayerDatabase;
import com.example.cric_manager.Core_1.Player;
import javafx.animation.KeyValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Pair;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountrywiseController {
    private Client main;
    @FXML
    TableView<Pair<String, Integer>> playertable;
    ObservableList<Pair<String, Integer>> data;
    @FXML
    TextField clubname,countryname;
    public void setMain(Client main) {
        this.main = main;
    }
    public void loadcol(){
//        playertable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<Pair<String, Integer>, String> country = new TableColumn<>("Country");
        country.setMinWidth(350);
        country.setCellValueFactory(new PropertyValueFactory<>("key"));

        TableColumn<Pair<String, Integer>, Integer> tot = new TableColumn<>("Total Players");
        tot.setMinWidth(350);
        tot.setCellValueFactory(new PropertyValueFactory<>("value"));


        playertable.getColumns().addAll(country,tot);
        playertable.setEditable(true);
    }
    public void show_table() throws Exception {
        loadcol();
        data= FXCollections.observableArrayList();
        //Add Products
        SocketWrapper socketWrapper = main.getSocketWrapper();
        socketWrapper.write("any");
        HashMap<String,Integer> mp= ((CricketPlayerDatabase)socketWrapper.read()).country_wise_count();
        for (Map.Entry<String, Integer> entry : mp.entrySet()) {
            data.add(new Pair<>(entry.getKey(), entry.getValue()));
        }

        playertable.setItems(data);
    }


    @FXML
    public void back(ActionEvent actionEvent) throws Exception {
        main.showSearchPlayer();
    }
}

