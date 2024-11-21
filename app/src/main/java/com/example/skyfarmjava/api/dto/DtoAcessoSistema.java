package com.example.skyfarmjava.api.dto;

import java.io.Serializable;

public class DtoAcessoSistema implements DtoModelo  {
    private String login;
    private String senha;

    private String log;
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

}
