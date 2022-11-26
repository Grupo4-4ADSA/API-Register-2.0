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

    public InfoEmpresaRelatorio(String nameManager, String corporateName, String cnpj, String nameBuilding, String publicPlace, Integer number, String cep) {
        this.nomeGestor = nameManager;
        this.nomeEmpresa = corporateName;
        this.cnpj = cnpj;
        this.nomePredio = nameBuilding;
        this.enderecoPredio = publicPlace;
        this.numeroEnderecoPredio = number;
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
