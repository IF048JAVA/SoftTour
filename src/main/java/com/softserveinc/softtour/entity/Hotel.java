package com.softserveinc.softtour.entity;

import javax.persistence.*;

@Entity
@Table(name = "Hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "stars", nullable = false, length = 1)
    private int stars;

    @Column(name = "rating")
    private double rating;

    @Column(name = "comfort")
    private double comfort;

    @Column(name = "cleanliness")
    private double cleanliness;

    @Column(name = "location")
    private double location;

    @Column(name = "valueForMoney")
    private double valueForMoney;

    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    public Hotel() {
    }

    public Hotel(String name, int stars, Region region) {

        this.name = name;
        this.stars = stars;
        this.region = region;
    }

    public Hotel(String name, int stars, double rating, double comfort,
                 double cleanliness, double location, double valueForMoney, Region region) {

        this.name = name;
        this.stars = stars;
        this.rating = rating;
        this.comfort = comfort;
        this.cleanliness = cleanliness;
        this.location = location;
        this.valueForMoney = valueForMoney;
        this.region = region;
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

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getComfort() {
        return comfort;
    }

    public void setComfort(double comfort) {
        this.comfort = comfort;
    }

    public double getCleanliness() {
        return cleanliness;
    }

    public void setCleanliness(double cleanliness) {
        this.cleanliness = cleanliness;
    }

    public double getLocation() {
        return location;
    }

    public void setLocation(double location) {
        this.location = location;
    }

    public double getValueForMoney() {
        return valueForMoney;
    }

    public void setValueForMoney(double valueForMoney) {
        this.valueForMoney = valueForMoney;
    }
}
