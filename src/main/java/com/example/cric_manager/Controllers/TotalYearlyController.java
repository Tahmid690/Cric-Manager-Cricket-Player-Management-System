package com.example.cric_manager.Controllers;

import com.example.cric_manager.Base.Client;
import com.example.cric_manager.Base.SocketWrapper;
import com.example.cric_manager.Core_1.CricketPlayerDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class TotalYearlyController {
    private Client main;
    @FXML
    TextField clubname;
    @FXML
    Text txt;
    public void setMain(Client main) {
        this.main = main;
    }
    @FXML
    public void sentclubname(ActionEvent actionEvent) throws Exception {
        String name = clubname.getText();
        System.out.println(name);
        SocketWrapper socketWrapper = main.getSocketWrapper();
        socketWrapper.write("any");
        txt.setText("Total Yearly Salary : $" + ((CricketPlayerDatabase)socketWrapper.read()).totalYearlySalary(name));
    }
    @FXML
    public void reset(ActionEvent actionEvent) {
        clubname.clear();
    }
    @FXML
    public void back(ActionEvent actionEvent) throws Exception {
        main.showSearchClub();
    }
}
