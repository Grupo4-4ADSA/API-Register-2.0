package com.autog.register.dto.response;

public class SucessResponse {
    private String msg;
    private Object objeto;

    public SucessResponse(String msg, Object objeto) {
        this.msg = msg;
        this.objeto = objeto;
    }

    public String getMsg() {
        return msg;
    }

    public Object getObjeto() {
        return objeto;
    }
}
