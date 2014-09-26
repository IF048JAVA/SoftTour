package com.softserveinc.softtour.entity;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Tour")
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "days", nullable = false)
    private int days;

    @Column(name = "departureCity", nullable = false)
    private String departureCity;

    @Column(name = "departureTime", nullable = false)
    private Date departureTime;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "food_id", nullable = false)
    private Food food;

    public Tour() {
    }

    public Tour(Date date, int days, String departureCity,
                Date departureTime, BigDecimal price, Hotel hotel, Food food) {

        this.date = date;
        this.days = days;
        this.departureCity = departureCity;
        this.departureTime = departureTime;
        this.price = price;
        this.hotel = hotel;
        this.food = food;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}
