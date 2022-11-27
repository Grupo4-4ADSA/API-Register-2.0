package com.autog.register.dto.response;

public class DadoGrafico {
    private String dataRelatorio;
    private Double totalKWM;
    private Double totalReais;

    public DadoGrafico(String dataRelatorio, Double totalKWM, Double totalReais) {
        this.dataRelatorio = dataRelatorio;
        this.totalKWM = totalKWM;
        this.totalReais = totalReais;
    }

    public String getDataRelatorio() {
        return dataRelatorio;
    }

    public void setDataRelatorio(String dataRelatorio) {
        this.dataRelatorio = dataRelatorio;
    }

    public Double getTotalKWM() {
        return totalKWM;
    }

    public void setTotalKWM(Double totalKWM) {
        this.totalKWM = totalKWM;
    }

    public Double getTotalReais() {
        return totalReais;
    }

    public void setTotalReais(Double totalReais) {
        this.totalReais = totalReais;
    }
}
