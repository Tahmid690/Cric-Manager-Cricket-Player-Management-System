package com.example.cric_manager.Base;

import java.io.Serializable;

public class LoginDTO implements Serializable {
    private String userName;
    private String password;
    private boolean status;
    public boolean sup;
    public LoginDTO(String name, String password){
        this.userName=name;
        this.password=password;
        this.status=false;
        this.sup=false;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


}
