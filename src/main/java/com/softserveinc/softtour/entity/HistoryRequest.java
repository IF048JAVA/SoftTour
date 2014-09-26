package com.softserveinc.softtour.entity;


import javax.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "HistoryRequest")
public class HistoryRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "cityFrom", nullable = false, length = 45)
    private String cityFrom;

    @Column(name = "dateFrom",  nullable = false)
    private Date dateFrom;

    @Column(name = "dateTo", nullable = false)
    private Date dateTo;

    @Column(name = "daysFrom", nullable = false)
    private int daysFrom;

    @Column(name = "daysTo", nullable = false)
    private int daysTo;

    @Column(name = "stars", nullable = false)
    @ElementCollection(targetClass=Integer.class)
    private Set stars;

    @Column(name = "adults", nullable = false)
    private int adults;

    @Column(name = "children", nullable = false)
    private int children;

    @Column(name = "priceFrom", nullable = false)
    private BigDecimal priceFrom;

    @Column(name = "priceTo", nullable = false)
    private BigDecimal priceTo;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(name = "requestDate", nullable = false)
    private Date requestDate;

    public HistoryRequest() {
    }

    public HistoryRequest(String cityFrom, Date dateFrom, Date dateTo,int daysFrom, int daysTo, Set stars, int adults, int children,
                          BigDecimal priceFrom, BigDecimal priceTo, User user, Country country, Date requestDate) {

        this.cityFrom = cityFrom;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.daysFrom = daysFrom;
        this.daysTo = daysTo;
        this.stars = stars;
        this.adults = adults;
        this.children = children;
        this.priceFrom = priceFrom;
        this.priceTo = priceTo;
        this.user = user;
        this.country = country;
        this.requestDate = requestDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public int getDaysFrom() {
        return daysFrom;
    }

    public void setDaysFrom(int daysFrom) {
        this.daysFrom = daysFrom;
    }

    public int getDaysTo() {
        return daysTo;
    }

    public void setDaysTo(int daysTo) {
        this.daysTo = daysTo;
    }

    public Set getStars() {
        return stars;
    }

    public void setStars(Set stars) {
        this.stars = stars;
    }

    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public BigDecimal getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(BigDecimal priceFrom) {
        this.priceFrom = priceFrom;
    }

    public BigDecimal getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(BigDecimal priceTo) {
        this.priceTo = priceTo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    @Override
    public String toString() {
        return "HistoryRequest{" +
                "id=" + id +
                ", requestDate=" + requestDate +
                ", country=" + country +
                ", user=" + user +
                ", priceTo=" + priceTo +
                ", priceFrom=" + priceFrom +
                ", children=" + children +
                ", adults=" + adults +
                ", stars=" + stars +
                ", daysTo=" + daysTo +
                ", daysFrom=" + daysFrom +
                ", dateTo=" + dateTo +
                ", dateFrom=" + dateFrom +
                ", cityFrom='" + cityFrom + '\'' +
                '}';
    }
}
