package br.com.projetofinal.login.models;

import java.io.Serializable;

public class AuthenticationRequest implements Serializable {


    private String username;
    private String password;

    private String newPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPasswordassword() {
        return newPassword;
    }

    public void setNewPassword(String password) {
        this.newPassword = password;
    }

    //need default constructor for JSON Parsing
    public AuthenticationRequest()
    {

    }

    public AuthenticationRequest(String username, String password, String newPassword) {
        this.setUsername(username);
        this.setPassword(password);
        this.setNewPassword(newPassword);
    }

}
