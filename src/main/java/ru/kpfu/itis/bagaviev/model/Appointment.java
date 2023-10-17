package ru.kpfu.itis.bagaviev.model;

import java.sql.Timestamp;

public class Appointment {
    private Integer id;
    private Integer masterId;
    private Integer serviceId;
    private Timestamp time;
    private String phone;

    public Appointment(Integer id, Integer masterId, Integer serviceId, Timestamp time, String phone) {
        this.id = id;
        this.masterId = masterId;
        this.serviceId = serviceId;
        this.time = time;
        this.phone = phone;
    }

    public Appointment(Integer masterId, Integer serviceId, Timestamp time, String phone) {
        this.masterId = masterId;
        this.serviceId = serviceId;
        this.time = time;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMasterId() {
        return masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
