package com.autog.register.dto.request;

public class RequisicaoInfoEmpresaRelatorio {

    private String gestorResponsavel;
    private String razaoSocial;
    private String cnpj;

    public RequisicaoInfoEmpresaRelatorio(String gestorResponsavel, String razaoSocial, String cnpj) {
        this.gestorResponsavel = gestorResponsavel;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
    }

    public String getGestorResponsavel() {
        return gestorResponsavel;
    }

    public void setGestorResponsavel(String gestorResponsavel) {
        this.gestorResponsavel = gestorResponsavel;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String toString() {
        return "ReportCompanyRequest{" +
                "gestorResponsavel='" + gestorResponsavel + '\'' +
                ", razaoSocial='" + razaoSocial + '\'' +
                ", cnpj='" + cnpj + '\'' +
                '}';
    }
}
