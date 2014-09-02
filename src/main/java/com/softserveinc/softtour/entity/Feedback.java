package com.softserveinc.softtour.entity;

import javax.persistence.*;

@Entity
@Table(name = "Feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "cleanliness", nullable = false, length = 1)
    private Integer cleanliness;

    @Column(name = "comfort", nullable = false, length = 1)
    private Integer comfort;

    @Column(name = "location", nullable = false, length = 1)
    private Integer location;

    @Column(name = "valueForMoney", nullable = false, length = 1)
    private Integer valueForMoney;

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

    public Feedback(Integer cleanliness, Integer comfort,
                    Integer location, Integer valueForMoney,
                    String comment, Hotel hotel, User user) {

        this.cleanliness = cleanliness;
        this.comfort = comfort;
        this.location = location;
        this.valueForMoney = valueForMoney;
        this.comment = comment;
        this.hotel = hotel;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCleanliness() {
        return cleanliness;
    }

    public void setCleanliness(Integer cleanliness) {
        this.cleanliness = cleanliness;
    }

    public Integer getComfort() {
        return comfort;
    }

    public void setComfort(Integer comfort) {
        this.comfort = comfort;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public Integer getValueForMoney() {
        return valueForMoney;
    }

    public void setValueForMoney(Integer valueForMoney) {
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
