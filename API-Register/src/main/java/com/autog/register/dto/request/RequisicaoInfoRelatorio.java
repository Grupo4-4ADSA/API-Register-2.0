package com.autog.register.dto.request;

public class RequisicaoInfoRelatorio {

    private String empresaPrestadora;
    private String dataRelatorio;
    private String bandeira;

    public RequisicaoInfoRelatorio(String empresaPrestadora, String dataRelatorio, String bandeira) {
        this.empresaPrestadora = empresaPrestadora;
        this.dataRelatorio = dataRelatorio;
        this.bandeira = bandeira;
    }

    public String getEmpresaPrestadora() {
        return empresaPrestadora;
    }

    public void setEmpresaPrestadora(String empresaPrestadora) {
        this.empresaPrestadora = empresaPrestadora;
    }

    public String getDataRelatorio() {
        return dataRelatorio;
    }

    public void setDataRelatorio(String dataRelatorio) {
        this.dataRelatorio = dataRelatorio;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    @Override
    public String toString() {
        return "ReportInfoRequest{" +
                "empresaPrestadora='" + empresaPrestadora + '\'' +
                ", dataRelatorio='" + dataRelatorio + '\'' +
                ", bandeira='" + bandeira + '\'' +
                '}';
    }
}
