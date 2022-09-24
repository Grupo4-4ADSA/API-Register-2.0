package com.autog.register.entity;

import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Empresa")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEmpresa")
    private Integer idEmpresa;

    @NotBlank
    @Column(name = "razaoSocial")
    private String razaoSocial;

    @NotBlank
    @CNPJ
    @Column(name = "cnpj")
    private String cnpj;

    @NotBlank
    @Column(name = "telefone")
    private String telefone;

    @NotBlank
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "dataAbertura")
    private LocalDate dataAbertura;

    @NotNull
    @Column(name = "ativa", columnDefinition = "TINYINT", length = 1)
    private boolean ativa;

    @OneToMany(mappedBy = "empresa")
    private List<Gestor> gestores = new ArrayList();

    @OneToMany(mappedBy = "empresa")
    private List<Predio> predios = new ArrayList();

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }
}
