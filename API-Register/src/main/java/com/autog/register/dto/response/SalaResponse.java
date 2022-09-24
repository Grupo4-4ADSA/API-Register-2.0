package com.autog.register.dto.response;

public class SalaResponse {

    private Integer idRoom;
    private String name;
    private Integer floor;

    public SalaResponse(Integer idRoom, String name, Integer floor) {
        this.idRoom = idRoom;
        this.name = name;
        this.floor = floor;
    }

    public Integer getIdRoom() {
        return idRoom;
    }

    public String getName() {
        return name;
    }

    public Integer getFloor() {
        return floor;
    }
}
