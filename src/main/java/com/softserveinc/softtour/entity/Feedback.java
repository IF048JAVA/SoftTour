package com.softserveinc.softtour.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "cleanliness", nullable = false, length = 1)
    private BigDecimal cleanliness;

    @Column(name = "comfort", nullable = false, length = 1)
    private BigDecimal comfort;

    @Column(name = "location", nullable = false, length = 1)
    private BigDecimal location;

    @Column(name = "valueForMoney", nullable = false, length = 1)
    private BigDecimal valueForMoney;

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Feedback() {
    }

    public Feedback(BigDecimal cleanliness, BigDecimal comfort,
                    BigDecimal location, BigDecimal valueForMoney,
                    String comment, Hotel hotel, User user) {

        this.cleanliness = cleanliness;
        this.comfort = comfort;
        this.location = location;
        this.valueForMoney = valueForMoney;
        this.comment = comment;
        this.hotel = hotel;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getCleanliness() {
        return cleanliness;
    }

    public void setCleanliness(BigDecimal cleanliness) {
        this.cleanliness = cleanliness;
    }

    public BigDecimal getComfort() {
        return comfort;
    }

    public void setComfort(BigDecimal comfort) {
        this.comfort = comfort;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
