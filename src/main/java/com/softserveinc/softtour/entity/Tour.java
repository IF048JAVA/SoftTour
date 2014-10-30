package com.softserveinc.softtour.entity;

import com.softserveinc.softtour.entity.template.Food;
import com.softserveinc.softtour.entity.template.RoomType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

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
    private Time departureTime;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "adultAmount", nullable = false)
    private int adultAmount;

    @Column(name = "childrenAmount", nullable = false)
    private int childrenAmount;

    @Column(name = "roomType", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    @Column(name = "food", nullable = false)
    @Enumerated(EnumType.STRING)
    private Food food;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    private String[] itTourId = new String[2];


    public Tour() {
    }

    public Tour(Date date, int days, String departureCity,
                Time departureTime, BigDecimal price, Hotel hotel, Food food) {

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

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getAdultAmount() {
        return adultAmount;
    }

    public void setAdultAmount(int adultAmount) {
        this.adultAmount = adultAmount;
    }

    public int getChildrenAmount() {
        return childrenAmount;
    }

    public void setChildrenAmount(int childrenAmount) {
        this.childrenAmount = childrenAmount;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public String[] getItTourId() {
        return itTourId;
    }

    public void setItTourId(String[] itTourId) {
        this.itTourId = itTourId;
    }

    @Override
    public String toString() {
        return "Tour{" +
                "id=" + id +
                ", date=" + date +
                ", days=" + days +
                ", departureCity='" + departureCity + '\'' +
                ", departureTime=" + departureTime +
                ", price=" + price +
                ", hotel=" + hotel +
                ", food=" + food +
                '}';
    }
}
