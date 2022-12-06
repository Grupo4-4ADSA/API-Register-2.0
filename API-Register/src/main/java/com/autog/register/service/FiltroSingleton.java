package com.autog.register.service;

public final class FiltroSingleton {

    private static FiltroSingleton instancia;
    private boolean equipamentoComRegistro;

    private FiltroSingleton() { }

    public boolean isEquipamentoComRegistro() {
        return equipamentoComRegistro;
    }

    public void setEquipamentoComRegistro(boolean equipamentoComRegistro) {
        this.equipamentoComRegistro = equipamentoComRegistro;
    }

    public static FiltroSingleton getInstancia() {
        if (instancia == null) {
            instancia = new FiltroSingleton();
        }
        return instancia;
    }
}
