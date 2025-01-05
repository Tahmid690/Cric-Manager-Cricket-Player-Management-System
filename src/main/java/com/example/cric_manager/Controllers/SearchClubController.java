package com.example.cric_manager.Controllers;

import com.example.cric_manager.Base.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class SearchClubController {

    private Client main;
    public void setMain(Client main) {
        this.main = main;
    }
    @FXML
    public void MaxSalary(ActionEvent action) throws Exception {
        main.showMaxSalaryClub();
    }
    @FXML
    public void MaxAge(ActionEvent action) throws Exception {
        main.showMaxAgeClub();
    }
    @FXML
    public void MaxHeight(ActionEvent action) throws Exception {
        main.showMaxHeightClub();
    }
    @FXML
    public void TotalClubSalary(ActionEvent action) throws Exception {
        main.totalyearlysalary();
    }
    @FXML
    public void Backmenu(ActionEvent action) throws Exception{
        main.showGuestPage();
    }

}
