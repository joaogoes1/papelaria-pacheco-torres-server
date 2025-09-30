package org.papelariapachecotorres.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthRequest {
    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    @JsonProperty("nome")
    public void setNome(String nome) { this.username = nome; }

    @JsonProperty("senha")
    public void setSenha(String senha) { this.password = senha; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
} 