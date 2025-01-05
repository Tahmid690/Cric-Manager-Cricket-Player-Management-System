package com.example.cric_manager.Controllers;

import com.example.cric_manager.Base.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class HomeController {
    private Client main;
    @FXML
    public void guestlogin(ActionEvent action){
        System.out.println("Guest Asche");
        try {
            main.showGuestPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void managerlogin(ActionEvent action) throws Exception {
        System.out.println("Ore Baba re Manager naki re");
        main.showLoginPgae();
    }

    public void setMain(Client main) {
        this.main = main;
    }

    public void back(ActionEvent actionEvent) {
        System.exit(0);
    }
}
