package com.autog.register.dto.request;


import java.time.LocalDate;

public class EquipamentoRequest {

    private String tipo;
    private LocalDate instalacao;
    private Integer vidaUtil;
    private Integer potencia;
    private Integer qtdEquipamento;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getInstalacao() {
        return instalacao;
    }

    public void setInstalacao(LocalDate instalacao) {
        this.instalacao = instalacao;
    }

    public Integer getVidaUtil() {
        return vidaUtil;
    }

    public void setVidaUtil(Integer vidaUtil) {
        this.vidaUtil = vidaUtil;
    }

    public Integer getPotencia() {
        return potencia;
    }

    public void setPotencia(Integer potencia) {
        this.potencia = potencia;
    }

    public Integer getQtdEquipamento() {
        return qtdEquipamento;
    }

    public void setQtdEquipamento(Integer qtdEquipamento) {
        this.qtdEquipamento = qtdEquipamento;
    }
}
