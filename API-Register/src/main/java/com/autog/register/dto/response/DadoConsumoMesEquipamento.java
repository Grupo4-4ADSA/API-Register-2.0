package com.autog.register.dto.response;

import com.autog.register.entity.CLNBox;
import com.autog.register.entity.Equipamento;

public class DadoConsumoMesEquipamento {

    private String nome;
    private String tipo;
    private CLNBox clnBox;
    private Double consumoKwm;
    private Double preco;

    public DadoConsumoMesEquipamento() {
    }

    public DadoConsumoMesEquipamento(String nome, String tipo, CLNBox clnBox) {
        this.nome = nome;
        this.tipo = tipo;
        this.clnBox = clnBox;
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

    public CLNBox getClnBox() {
        return clnBox;
    }

    public void setClnBox(CLNBox clnBox) {
        this.clnBox = clnBox;
    }

    public Double getConsumoKwm() {
        return consumoKwm;
    }

    public void setConsumoKwm(Double consumoKwm) {
        this.consumoKwm = consumoKwm;
    }

    public Double getPreco() {
        return getConsumoKwm() * 0.28738;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
