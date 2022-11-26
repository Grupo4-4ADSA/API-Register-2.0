package com.autog.register.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Registro")
public class Registro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRegistro")
    private Integer idRegister;

    @Column(name = "ligado")
    private boolean on;

    @Column(name = "data")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "fkEquipamento", referencedColumnName = "idEquipamento")
    private Equipamento equipment;

    public Integer getIdRegister() {
        return idRegister;
    }

    public void setIdRegister(Integer idRegister) {
        this.idRegister = idRegister;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Equipamento getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipamento equipment) {
        this.equipment = equipment;
    }
}
