package com.autog.register.entity;

import javax.persistence.*;

@Entity
@Table(name = "Relatorio")
public class Relatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRelatorio;
    private String empresaContratada;
    private String dataRelatorio;
    private String bandeira;
    private String gestorResponsavel;
    private String empresa;
    private String cnpj;
    private Double totalKwm;
    private Double totalReais;
    private String nomeArquivo;

    // Relacionamento com predio
    @ManyToOne
    @JoinColumn(name = "fkPredio", referencedColumnName = "idPredio")
    private Predio predio;


    public Relatorio() {
    }

//    public Relatorio(String empresaContratada, String dataRelatorio, String bandeira, String gestorResponsavel, String empresa, String cnpj, Double totalKwm, Double totalReais, String nomeArquivo) {
//        this.empresaContratada = empresaContratada;
//        this.dataRelatorio = dataRelatorio;
//        this.bandeira = bandeira;
//        this.gestorResponsavel = gestorResponsavel;
//        this.empresa = empresa;
//        this.cnpj = cnpj;
//        this.totalKwm = totalKwm;
//        this.totalReais = totalReais;
//        this.nomeArquivo = nomeArquivo;
//
//    }

    public Relatorio(String empresaContratada, String dataRelatorio, String bandeira, String gestorResponsavel, String empresa, String cnpj, Double totalKwm, Double totalReais, String nomeArquivo, Predio predio) {
        this.empresaContratada = empresaContratada;
        this.dataRelatorio = dataRelatorio;
        this.bandeira = bandeira;
        this.gestorResponsavel = gestorResponsavel;
        this.empresa = empresa;
        this.cnpj = cnpj;
        this.totalKwm = totalKwm;
        this.totalReais = totalReais;
        this.nomeArquivo = nomeArquivo;
        this.predio = predio;
    }

    public Integer getIdRelatorio() {
        return idRelatorio;
    }

    public void setIdRelatorio(Integer idRelatorio) {
        this.idRelatorio = idRelatorio;
    }
}
