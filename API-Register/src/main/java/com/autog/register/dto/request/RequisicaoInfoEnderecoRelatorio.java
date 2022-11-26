package com.autog.register.dto.request;

public class RequisicaoInfoEnderecoRelatorio {

    private String nomeEdificio;
    private String logradouro;
    private Integer numero;
    private String cep;

    public RequisicaoInfoEnderecoRelatorio(String nomeEdificio, String logradouro, Integer numero, String cep) {
        this.nomeEdificio = nomeEdificio;
        this.logradouro = logradouro;
        this.numero = numero;
        this.cep = cep;
    }

    public String getNomeEdificio() {
        return nomeEdificio;
    }

    public void setNomeEdificio(String nomeEdificio) {
        this.nomeEdificio = nomeEdificio;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public String toString() {
        return "ReportAddressRequest{" +
                "nomeEdificio='" + nomeEdificio + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", numero=" + numero +
                ", cep='" + cep + '\'' +
                '}';
    }
}
