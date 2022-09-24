package com.autog.register.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "Equipamento")
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEquipamento")
    private Integer idEquipamento;

    @NotBlank
    @Column(name = "nome")
    private String nome;

    @NotBlank
    @Column(name = "tipo")
    private String tipo;

    @NotNull
    @Column(name = "instalacao")
    private LocalDate instalacao;

    @Column(name = "vidaUtil")
    private Integer vidaUtil;

    @NotNull
    @Column(name = "potencia")
    private Integer potencia;

    @Column(name = "qtdEquipamento")
    private Integer qtdEquipamento;

    @Column(name = "porta")
    private Integer porta;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "fkCLNBox", referencedColumnName = "idCLNBox")
    private CLNBox clnBox;

    // Relacionamento com sensoPresenca, sensorLuminosidade


    public Integer getIdEquipamento() {
        return idEquipamento;
    }

    public void setIdEquipamento(Integer idEquipamento) {
        this.idEquipamento = idEquipamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getInstalacao() {
        return instalacao;
    }

    public void setInstalacao(LocalDate instalacao) {
        this.instalacao = instalacao;
    }

    public Integer getVidaUtil() {
        return vidaUtil;
    }

    public void setVidaUtil(Integer vidaUtil) {
        this.vidaUtil = vidaUtil;
    }

    public Integer getPotencia() {
        return potencia;
    }

    public void setPotencia(Integer potencia) {
        this.potencia = potencia;
    }

    public Integer getQtdEquipamento() {
        return qtdEquipamento;
    }

    public void setQtdEquipamento(Integer qtdEquipamento) {
        this.qtdEquipamento = qtdEquipamento;
    }

    public Integer getPorta() {
        return porta;
    }

    public void setPorta(Integer porta) {
        this.porta = porta;
    }

    public CLNBox getClnBox() {
        return clnBox;
    }

    public void setClnBox(CLNBox clnBox) {
        this.clnBox = clnBox;
    }
}
