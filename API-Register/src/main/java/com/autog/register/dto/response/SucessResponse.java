package com.autog.register.dto.response;

public class SucessResponse {
    private Boolean sucesso;

    public SucessResponse(Boolean sucesso) {
        this.sucesso = sucesso;
    }

    public Boolean getSucesso() {
        return sucesso;
    }
}
