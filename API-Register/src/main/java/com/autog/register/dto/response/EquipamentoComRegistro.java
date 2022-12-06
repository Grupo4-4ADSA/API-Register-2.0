package com.autog.register.dto.response;

import com.autog.register.entity.CLNBox;
import com.autog.register.entity.Registro;

import java.time.LocalDate;

public class EquipamentoComRegistro {

    private Integer idEquipamento;
    private String nome;
    private String tipo;
    private LocalDate instalacao;
    private Integer vidaUtil;
    private Integer potencia;
    private Integer qtdEquipamento;
    private Integer porta;
    private CLNBox clnBox;
    private Registro registro;

    public EquipamentoComRegistro(Integer idEquipamento, String nome, String tipo, LocalDate instalacao, Integer vidaUtil, Integer potencia, Integer qtdEquipamento, Integer porta, CLNBox clnBox, Registro registro) {
        this.idEquipamento = idEquipamento;
        this.nome = nome;
        this.tipo = tipo;
        this.instalacao = instalacao;
        this.vidaUtil = vidaUtil;
        this.potencia = potencia;
        this.qtdEquipamento = qtdEquipamento;
        this.porta = porta;
        this.clnBox = clnBox;
        this.registro = registro;
    }

    public Integer getIdEquipamento() {
        return idEquipamento;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public LocalDate getInstalacao() {
        return instalacao;
    }

    public Integer getVidaUtil() {
        return vidaUtil;
    }

    public Integer getPotencia() {
        return potencia;
    }

    public Integer getQtdEquipamento() {
        return qtdEquipamento;
    }

    public Integer getPorta() {
        return porta;
    }

    public CLNBox getClnBox() {
        return clnBox;
    }

    public Registro getRegistro() {
        return registro;
    }
}
