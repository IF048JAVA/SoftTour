package com.softserveinc.softtour.entity;

import javax.persistence.*;

@Entity
@Table(name = "Feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "cleanliness", nullable = false, length = 1)
    private int cleanliness;

    @Column(name = "comfort", nullable = false, length = 1)
    private int comfort;

    @Column(name = "location", nullable = false, length = 1)
    private int location;

    @Column(name = "valueForMoney", nullable = false, length = 1)
    private int valueForMoney;

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

    public Feedback(int cleanliness, int comfort,
                    int location, int valueForMoney,
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

    public int getCleanliness() {
        return cleanliness;
    }

    public void setCleanliness(int cleanliness) {
        this.cleanliness = cleanliness;
    }

    public int getComfort() {
        return comfort;
    }

    public void setComfort(int comfort) {
        this.comfort = comfort;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public int getValueForMoney() {
        return valueForMoney;
    }

    public void setValueForMoney(int valueForMoney) {
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
