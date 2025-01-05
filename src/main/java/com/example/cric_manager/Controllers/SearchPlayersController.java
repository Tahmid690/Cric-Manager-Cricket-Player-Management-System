package com.example.cric_manager.Controllers;

import com.example.cric_manager.Base.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class SearchPlayersController {

    private Client main;
    public void setMain(Client main) {
        this.main = main;
    }
    @FXML
    public void ByPlayerName(ActionEvent action) throws Exception {
        main.showbyname();
    }
    @FXML
    public void ByClubandCountry(ActionEvent action) throws Exception {
        main.showbyclubandcountry();
    }
    @FXML
    public void BySalaryRange(ActionEvent action) throws Exception {
        main.showbysalary();
    }
    @FXML
    public void ByPosition(ActionEvent action) throws Exception {
        main.showbyposition();
    }
    @FXML
    public void Countrywisecount(ActionEvent action) throws Exception {
        main.showcountrywise();
    }
    @FXML
    public void BackMain(ActionEvent action) throws Exception{
        main.showGuestPage();
    }
}



