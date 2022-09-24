package com.autog.register.dto.response;

import java.util.Date;

public class EquipamentoResponse {
    private Integer idRoom;
    private String nameRoom;
    private Integer floor;
    private Integer idEquipment;
    private String nameEquipment;
    private String type;
    private Date installationDate;
    private Integer lifespan;
    private Integer potency;
    private Integer qtdEquipment;
    private Integer door;

    public EquipamentoResponse(Integer idRoom, String nameRoom, Integer floor, Integer idEquipment, String nameEquipment, String type, Date installationDate, Integer lifespan, Integer potency, Integer qtdEquipment, Integer door) {
        this.idRoom = idRoom;
        this.nameRoom = nameRoom;
        this.floor = floor;
        this.idEquipment = idEquipment;
        this.nameEquipment = nameEquipment;
        this.type = type;
        this.installationDate = installationDate;
        this.lifespan = lifespan;
        this.potency = potency;
        this.qtdEquipment = qtdEquipment;
        this.door = door;
    }

    public Integer getIdRoom() {
        return idRoom;
    }

    public String getNameRoom() {
        return nameRoom;
    }

    public Integer getFloor() {
        return floor;
    }

    public Integer getIdEquipment() {
        return idEquipment;
    }

    public String getNameEquipment() {
        return nameEquipment;
    }

    public String getType() {
        return type;
    }

    public Date getInstallationDate() {
        return installationDate;
    }

    public Integer getLifespan() {
        return lifespan;
    }

    public Integer getPotency() {
        return potency;
    }

    public Integer getQtdEquipment() {
        return qtdEquipment;
    }

    public Integer getDoor() {
        return door;
    }
}
