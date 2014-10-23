package com.softserveinc.softtour.entity;

import javax.persistence.*;

@Entity
@Table(name = "Country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name", nullable = true, length = 45)
    private String name;

    @Column(name = "itTourId", nullable = true, length = 20)
    private Long itTourId;

    public Country() {
    }

    public Country(String name) {
        this.name = name;
    }

    public Country(String name, Long itTourId) {
        this.name = name;
        this.itTourId = itTourId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getItTourId() {
        return itTourId;
    }

    public void setItTourId(Long itTourId) {
        this.itTourId = itTourId;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
