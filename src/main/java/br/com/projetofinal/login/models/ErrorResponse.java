package br.com.projetofinal.login.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class ErrorResponse implements Serializable {

    private final String mensagem;
    private Boolean isBlocked;

    public ErrorResponse(String mensagem, Boolean isBlocked) {
        this.mensagem = mensagem;
        this.isBlocked = isBlocked;
    }
}
