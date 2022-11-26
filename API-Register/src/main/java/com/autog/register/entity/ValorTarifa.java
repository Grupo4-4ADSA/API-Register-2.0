package com.autog.register.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Entity
@Table(name = "valorTarifa")
public class ValorTarifa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idValorTarifa")
    private Integer idValorTarifa;

    @NotNull
    @Column(name = "valorTarifaKwh")
    private Double valorTarifaKwh;

    @Column(name = "bandeira")
    private String bandeira;

    @PastOrPresent
    @Column(name = "date")
    private LocalDateTime date;

    public Integer getIdValorTarifa() {
        return idValorTarifa;
    }

    public void setIdValorTarifa(Integer idValorTarifa) {
        this.idValorTarifa = idValorTarifa;
    }

    public Double getValorTarifaKwh() {
        return valorTarifaKwh;
    }

    public void setValorTarifaKwh(Double valorTarifaKwh) {
        this.valorTarifaKwh = valorTarifaKwh;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
