package com.autog.register.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Predio")
public class Predio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPredio")
    private Integer idPredio;

    @Column(name = "nome")
    private String nomePredio;

    @Column(name = "andares")
    private Integer andares;

    @Column(name = "subsolos")
    private Integer subsolos;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fkEmpresa", referencedColumnName = "idEmpresa")
    private Empresa empresa;

    public List<Sala> getSalas() {
        return salas;
    }

    public void setSalas(List<Sala> salas) {
        this.salas = salas;
    }

    public List<Gestor> getGestores() {
        return gestores;
    }

    public void setGestores(List<Gestor> gestores) {
        this.gestores = gestores;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "predio")
    private List<Sala> salas = new ArrayList();

    @JsonIgnore
    @OneToMany(mappedBy = "predio")
    private List<Gestor> gestores = new ArrayList();

    // Relacionamento com relatorio

    public Integer getIdPredio() {
        return idPredio;
    }

    public void setIdPredio(Integer idPredio) {
        this.idPredio = idPredio;
    }

    public String getNomePredio() {
        return nomePredio;
    }

    public void setNomePredio(String nomePredio) {
        this.nomePredio = nomePredio;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Integer getAndares() {
        return andares;
    }

    public void setAndares(Integer andares) {
        this.andares = andares;
    }

    public Integer getSubsolos() {
        return subsolos;
    }

    public void setSubsolos(Integer subsolos) {
        this.subsolos = subsolos;
    }
}
