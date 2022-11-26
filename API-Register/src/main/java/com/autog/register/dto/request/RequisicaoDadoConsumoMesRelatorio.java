package com.autog.register.dto.request;

public class RequisicaoDadoConsumoMesRelatorio {

    private String sala;
    private Integer andar;
    private Double consumoKwm;
    private Double consumoReais;

    public RequisicaoDadoConsumoMesRelatorio(String sala, Integer andar, Double consumoKwm, Double consumoReais) {
        this.sala = sala;
        this.andar = andar;
        this.consumoKwm = consumoKwm;
        this.consumoReais = consumoReais;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public Integer getAndar() {
        return andar;
    }

    public void setAndar(Integer andar) {
        this.andar = andar;
    }

    public Double getConsumoKwm() {
        return consumoKwm;
    }

    public void setConsumoKwm(Double consumoKwm) {
        this.consumoKwm = consumoKwm;
    }

    public Double getConsumoReais() {
        return consumoReais;
    }

    public void setConsumoReais(Double consumoReais) {
        this.consumoReais = consumoReais;
    }

    @Override
    public String toString() {
        return "ReportMonthlyConsumptionRequest{" +
                "sala='" + sala + '\'' +
                ", andar=" + andar +
                ", consumoKwm=" + consumoKwm +
                ", consumoReais=" + consumoReais +
                '}';
    }
}
