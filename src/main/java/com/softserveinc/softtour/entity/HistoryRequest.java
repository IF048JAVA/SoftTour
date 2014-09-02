package com.softserveinc.softtour.entity;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "HistoryRequest")
public class HistoryRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "cityFrom", nullable = false, length = 45)
    private String cityFrom;

    @Column(name = "dateFrom",  nullable = false)
    private Date dateFrom;

    @Column(name = "dateTo", nullable = false)
    private Date dateTo;

    @Column(name = "daysFrom", nullable = false)
    private Integer daysFrom;

    @Column(name = "daysTo", nullable = false)
    private Integer daysTo;

    @Column(name = "stars", nullable = false)
    private Set stars;

    @Column(name = "adults", nullable = false)
    private Integer adults;

    @Column(name = "children", nullable = false)
    private Integer children;

    @Column(name = "RequestDate", nullable = false)
    private Date requestDate;

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

    public HistoryRequest() {
    }

    public HistoryRequest(String cityFrom, Date dateFrom, Date dateTo,
                          Integer daysFrom, Integer daysTo, Set stars,
                          Integer adults, Integer children, Date requestDate,
                          BigDecimal priceFrom, BigDecimal priceTo, User user, Country country) {

        this.cityFrom = cityFrom;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.daysFrom = daysFrom;
        this.daysTo = daysTo;
        this.stars = stars;
        this.adults = adults;
        this.children = children;
        this.requestDate = requestDate;
        this.priceFrom = priceFrom;
        this.priceTo = priceTo;
        this.user = user;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Integer getDaysFrom() {
        return daysFrom;
    }

    public void setDaysFrom(Integer daysFrom) {
        this.daysFrom = daysFrom;
    }

    public Integer getDaysTo() {
        return daysTo;
    }

    public void setDaysTo(Integer daysTo) {
        this.daysTo = daysTo;
    }

    public Set getStars() {
        return stars;
    }

    public void setStars(Set stars) {
        this.stars = stars;
    }

    public Integer getAdults() {
        return adults;
    }

    public void setAdults(Integer adults) {
        this.adults = adults;
    }

    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
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
}
