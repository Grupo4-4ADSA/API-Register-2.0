package com.autog.register.dto.response;

public class InfoEmpresaRelatorio {

    private String nomeGestor;
    private String nomeEmpresa;
    private String cnpj;
    private String nomePredio;
    private String enderecoPredio;
    private Integer numeroEnderecoPredio;
    private String cep;
//    private Double totalKwm;
//    private Double totalValor;


    public InfoEmpresaRelatorio(String nomeGestor, String nomeEmpresa, String cnpj, String nomePredio) {
        this.nomeGestor = nomeGestor;
        this.nomeEmpresa = nomeEmpresa;
        this.cnpj = cnpj;
        this.nomePredio = nomePredio;
    }

    public InfoEmpresaRelatorio(String enderecoPredio, Integer numeroEnderecoPredio, String cep) {
        this.enderecoPredio = enderecoPredio;
        this.numeroEnderecoPredio = numeroEnderecoPredio;
        this.cep = cep;
    }

    public String getNomeGestor() {
        return nomeGestor;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getNomePredio() {
        return nomePredio;
    }

    public String getEnderecoPredio() {
        return enderecoPredio;
    }

    public Integer getNumeroEnderecoPredio() {
        return numeroEnderecoPredio;
    }

    public String getCep() {
        return cep;
    }


    //
//    public Double getTotalKwm() {
//        return totalKwm;
//    }
//
//    public void setTotalKwm(Double totalKwm) {
//        this.totalKwm = totalKwm;
//    }
//
//    public Double getTotalValor() {
//        return totalValor;
//    }
//
//    public void setTotalValor(Double totalValor) {
//        this.totalValor = totalValor;
//    }
}
