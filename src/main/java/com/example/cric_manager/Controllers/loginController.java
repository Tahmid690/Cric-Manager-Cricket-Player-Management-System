package com.example.cric_manager.Controllers;

import com.example.cric_manager.Base.Client;
import com.example.cric_manager.Base.ClientThread;
import com.example.cric_manager.Base.LoginDTO;
import com.example.cric_manager.Base.SocketWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class loginController {
    private Client main;
    @FXML
    TextField name;
    @FXML
    PasswordField pass;
    public void setMain(Client main) {
        this.main = main;
    }

    @FXML
    public void login(ActionEvent actionEvent) throws Exception {
        String name = this.name.getText();
        String pass = this.pass.getText();
        LoginDTO s = new LoginDTO(name,pass);

        SocketWrapper socketWrapper = main.getSocketWrapper();

        socketWrapper.write(s);
//        Thread.sleep(1000);
        s = (LoginDTO) socketWrapper.read();

        if(!s.isStatus()){
            main.showAlert();
        }
        else{
            main.islog=true;
//            new ClientThread(socketWrapper,s.getUserName(),main).start();
            main.showclubWindow(s.getUserName());
        }


    }

    @FXML
    public void SignUP(ActionEvent actionEvent) throws Exception {
        main.showSignUp();
    }

    @FXML
    public void back(ActionEvent actionEvent) throws Exception {
        main.showMainPage();
    }
}
