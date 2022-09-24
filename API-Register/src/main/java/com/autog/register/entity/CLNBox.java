package com.autog.register.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CLNBox")
public class CLNBox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idClnbox")
    private Integer idCLNBox;

    @Column(name = "qrCode")
    private String qrCode;

    @Column(name = "ip")
    private String ip;

    @NotNull
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "fkSala", referencedColumnName = "idSala")
    private Sala sala;

    public Integer getIdCLNBox() {
        return idCLNBox;
    }

    public void setIdCLNBox(Integer idCLNBox) {
        this.idCLNBox = idCLNBox;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

}