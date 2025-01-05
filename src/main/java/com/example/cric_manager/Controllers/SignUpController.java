package com.example.cric_manager.Controllers;

import com.example.cric_manager.Base.Client;
import com.example.cric_manager.Base.LoginDTO;
import com.example.cric_manager.Base.SocketWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class SignUpController {
    @FXML
    TextField nam;
    @FXML
    PasswordField pas,conpass;
    private Client main;
    public void setMain(Client main) {
        this.main = main;
    }

    @FXML
    public void SignUp(ActionEvent actionEvent) throws Exception {
        String name = this.nam.getText();
        String pass = this.pas.getText();
        String pass2= this.conpass.getText();
        nam.clear();
        pas.clear();
        conpass.clear();
        if(!pass.equals(pass2)){
            main.showAlertpass();
            return;
        }
        LoginDTO s = new LoginDTO(name,pass);
        s.sup = true;

        SocketWrapper socketWrapper = main.getSocketWrapper();

        socketWrapper.write(s);
        main.showAlertSucessSignUp();
    }

    @FXML
    public void back(ActionEvent actionEvent) throws Exception {
        main.showLoginPgae();
    }
}
