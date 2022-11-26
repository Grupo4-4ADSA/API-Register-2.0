package com.autog.register.dto.request;

public class EquipamentoRelatorio {

    private Integer idPredio;
    private Integer mes;
    private Integer ano;

    public EquipamentoRelatorio() {
    }

    public EquipamentoRelatorio(Integer idPredio, Integer mes, Integer ano) {
        this.idPredio = idPredio;
        this.mes = mes;
        this.ano = ano;
    }

    public Integer getIdPredio() {
        return idPredio;
    }

    public void setIdPredio(Integer idPredio) {
        this.idPredio = idPredio;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }
}