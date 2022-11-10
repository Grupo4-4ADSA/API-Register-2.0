package com.autog.register.dto.response;

public class LoginResponse {
    private Integer idGestor;
    private String nome;
    private Integer idPredio;

    public LoginResponse(Integer idGestor, String nome, Integer idPredio) {
        this.idGestor = idGestor;
        this.nome = nome;
        this.idPredio = idPredio;
    }

    public Integer getIdGestor() {
        return idGestor;
    }

    public String getNome() {
        return nome;
    }

    public Integer getIdPredio() {
        return idPredio;
    }
}
