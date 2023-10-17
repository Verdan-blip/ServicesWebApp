package ru.kpfu.itis.bagaviev.model;

public class MasterService {

    private Integer id;
    private Integer masterId;
    private Integer serviceId;

    public MasterService(Integer id, Integer masterId, Integer serviceId) {
        this.id = id;
        this.masterId = masterId;
        this.serviceId = serviceId;
    }

    public MasterService(Integer masterId, Integer serviceId) {
        this.masterId = masterId;
        this.serviceId = serviceId;
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
}
