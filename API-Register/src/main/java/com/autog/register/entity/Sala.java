package com.autog.register.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Sala")
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSala")
    private Integer idRoom;

    @NotBlank
    @Column(name = "nome")
    private String name;

    @NotNull
    @Column(name = "andar")
    private Integer floor;

    @ManyToOne
    @JoinColumn(name = "fkPredio", referencedColumnName = "idPredio")
    private Predio predio;

    @OneToMany(mappedBy = "sala")
    private List<Agendamento> agendamentos = new ArrayList();

    @OneToOne(mappedBy = "sala")
    private CLNBox clnBox;

    @JsonIgnore
    public CLNBox getClnBox() {
        return clnBox;
    }

    public void setClnBox(CLNBox clnBox) {
        this.clnBox = clnBox;
    }

    @JsonIgnore
    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }

    public Integer getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Integer idRoom) {
        this.idRoom = idRoom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Predio getPredio() {
        return predio;
    }

    public void setPredio(Predio predio) {
        this.predio = predio;
    }
}
