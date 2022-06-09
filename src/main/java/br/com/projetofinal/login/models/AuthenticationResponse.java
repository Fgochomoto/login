package br.com.projetofinal.login.models;

import lombok.Data;

import java.io.Serializable;
@Data
public class AuthenticationResponse implements Serializable {

    private final String jwt;
    private Boolean isBlocked;

    public AuthenticationResponse(String jwt, Boolean isBlocked) {
        this.jwt = jwt;
        this.isBlocked = isBlocked;
    }
}
