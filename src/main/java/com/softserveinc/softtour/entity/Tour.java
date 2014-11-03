package com.softserveinc.softtour.entity;

import com.softserveinc.softtour.entity.template.Food;
import com.softserveinc.softtour.entity.template.RoomType;

import javax.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.Arrays;

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

    @Transient
    private String[] itTourId = new String[2];

    public Tour() {}

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + adultAmount;
		result = prime * result + childrenAmount;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + days;
		result = prime * result
				+ ((departureCity == null) ? 0 : departureCity.hashCode());
		result = prime * result
				+ ((departureTime == null) ? 0 : departureTime.hashCode());
		result = prime * result + ((food == null) ? 0 : food.hashCode());
		result = prime * result + ((hotel == null) ? 0 : hotel.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + Arrays.hashCode(itTourId);
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result
				+ ((roomType == null) ? 0 : roomType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tour other = (Tour) obj;
		if (adultAmount != other.adultAmount)
			return false;
		if (childrenAmount != other.childrenAmount)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (days != other.days)
			return false;
		if (departureCity == null) {
			if (other.departureCity != null)
				return false;
		} else if (!departureCity.equals(other.departureCity))
			return false;
		if (departureTime == null) {
			if (other.departureTime != null)
				return false;
		} else if (!departureTime.equals(other.departureTime))
			return false;
		if (food != other.food)
			return false;
		if (hotel == null) {
			if (other.hotel != null)
				return false;
		} else if (!hotel.equals(other.hotel))
			return false;
		if (id != other.id)
			return false;
		if (!Arrays.equals(itTourId, other.itTourId))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (roomType != other.roomType)
			return false;
		return true;
	}
    
}
