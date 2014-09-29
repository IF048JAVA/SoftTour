package com.softserveinc.softtour.entity;

import javax.persistence.*;
import java.math.BigDecimal;

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
    private BigDecimal rating;

    @Column(name = "comfort")
    private BigDecimal comfort;

    @Column(name = "cleanliness")
    private BigDecimal cleanliness;

    @Column(name = "location")
    private BigDecimal location;

    @Column(name = "valueForMoney")
    private BigDecimal valueForMoney;

    @Column(name = "imgUrl")
    private String imgUrl;

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

    public Hotel(String name, int stars, BigDecimal rating, BigDecimal comfort, BigDecimal cleanliness, BigDecimal location, BigDecimal valueForMoney, String imgUrl, Region region) {
        this.name = name;
        this.stars = stars;
        this.rating = rating;
        this.comfort = comfort;
        this.cleanliness = cleanliness;
        this.location = location;
        this.valueForMoney = valueForMoney;
        this.imgUrl = imgUrl;
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

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public BigDecimal getComfort() {
        return comfort;
    }

    public void setComfort(BigDecimal comfort) {
        this.comfort = comfort;
    }

    public BigDecimal getCleanliness() {
        return cleanliness;
    }

    public void setCleanliness(BigDecimal cleanliness) {
        this.cleanliness = cleanliness;
    }

    public BigDecimal getLocation() {
        return location;
    }

    public void setLocation(BigDecimal location) {
        this.location = location;
    }

    public BigDecimal getValueForMoney() {
        return valueForMoney;
    }

    public void setValueForMoney(BigDecimal valueForMoney) {
        this.valueForMoney = valueForMoney;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
