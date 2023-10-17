package ru.kpfu.itis.bagaviev.model;

public class Service {

    private Integer id;
    private String name;

    public Service(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Service(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
