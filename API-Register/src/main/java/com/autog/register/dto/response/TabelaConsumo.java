package com.autog.register.dto.response;

public class TabelaConsumo {

    private String empresaPrestadora;
    private String dataRelatorio;
    private String bandeira;
    private String nomeResponsavel;
    private String razaoSocial;
    private String cnpj;
    private String nomePredio;
    private String logradouro;
    private Integer numeroEndereco;
    private String cep;
    private Double totalKWM;
    private Double totalReais;

    public TabelaConsumo(String empresaPrestadora, String dataRelatorio, String bandeira, String nomeResponsavel, String razaoSocial, String cnpj, String nomePredio, String logradouro, Integer numeroEndereco, String cep, Double totalKWM, Double totalReais) {
        this.empresaPrestadora = empresaPrestadora;
        this.dataRelatorio = dataRelatorio;
        this.bandeira = bandeira;
        this.nomeResponsavel = nomeResponsavel;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.nomePredio = nomePredio;
        this.logradouro = logradouro;
        this.numeroEndereco = numeroEndereco;
        this.cep = cep;
        this.totalKWM = totalKWM;
        this.totalReais = totalReais;
    }

    public String getEmpresaPrestadora() {
        return empresaPrestadora;
    }

    public String getDataRelatorio() {
        return dataRelatorio;
    }

    public String getBandeira() {
        return bandeira;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getNomePredio() {
        return nomePredio;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public Integer getNumeroEndereco() {
        return numeroEndereco;
    }

    public String getCep() {
        return cep;
    }

    public Double getTotalKWM() {
        return totalKWM;
    }

    public Double getTotalReais() {
        return totalReais;
    }
}
