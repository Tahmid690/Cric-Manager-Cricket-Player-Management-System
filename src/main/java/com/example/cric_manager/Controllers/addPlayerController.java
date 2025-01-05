package com.example.cric_manager.Controllers;

import com.example.cric_manager.Base.Client;
import com.example.cric_manager.Base.SocketWrapper;
import com.example.cric_manager.Core_1.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.net.ServerSocket;


public class addPlayerController {
    private Client main;
    SocketWrapper socketWrapper;


    @FXML
    private TextField name,country,age,height,club,position,number,salary;
    public void setMain(Client main) {
        this.main = main;
    }
    @FXML
    public void addplr(ActionEvent actionEvent) throws Exception {
        Player p = new Player(name.getText(),country.getText(),Integer.parseInt(age.getText()),Double.parseDouble(height.getText()),club.getText(),position.getText(),Integer.parseInt(number.getText()),Integer.parseInt(salary.getText()));
        socketWrapper = main.getSocketWrapper();
        name.clear();
        country.clear();
        age.clear();
        height.clear();
        club.clear();
        position.clear();
        number.clear();
        salary.clear();
        socketWrapper.write(p);
        main.addsuc();

    }

    public void back(ActionEvent actionEvent) throws Exception {
        main.showGuestPage();
    }
}
