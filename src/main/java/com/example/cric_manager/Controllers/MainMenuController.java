package com.example.cric_manager.Controllers;

import com.example.cric_manager.Base.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


public class MainMenuController {
    private Client main;
    @FXML
    public void OnClickSearchClub(ActionEvent action) throws Exception{
        System.out.println("Club Khujo");
        main.showSearchClub();
    }
    @FXML
    public void BackHome(ActionEvent action){
        System.out.println("Ferot Jao");
        try {
            main.showMainPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void OnClickSearchPlayer(ActionEvent action) throws Exception{
        System.out.println("Kheloar Khujo");
        main.showSearchPlayer();
    }
    @FXML
    public void OnClickAddPlayer(ActionEvent action) throws Exception {
        System.out.println("Kheloar Dhukao");
        main.showaddPlayer();
    }
    public void setMain(Client main) {
        this.main = main;
    }

}
