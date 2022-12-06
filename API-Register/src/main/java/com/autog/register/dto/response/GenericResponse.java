package com.autog.register.dto.response;

public class GenericResponse {
    private boolean sucesso;

    public GenericResponse(boolean sucesso) {
        this.sucesso = sucesso;
    }

    public boolean isSucesso() {
        return sucesso;
    }
}
