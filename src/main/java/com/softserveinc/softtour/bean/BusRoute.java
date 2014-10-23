package com.softserveinc.softtour.bean;

public class BusRoute {

    private String id;
    private String departureCity;
    private String arrivalCity;
    private String departureDate;
    private String departureTime;
    private String onWayTime;
    private String arrivalTime;
    private String priceMin;
    private String priceMax;

    public BusRoute() {}

    public BusRoute(String id, String departureCity, String arrivalCity, String departureDate, String departureTime,
                    String onWayTime, String arrivalTime, String priceMin, String priceMax) {
        this.id = id;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.onWayTime = onWayTime;
        this.arrivalTime = arrivalTime;
        this.priceMin = priceMin;
        this.priceMax = priceMax;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getOnWayTime() {
        return onWayTime;
    }

    public void setOnWayTime(String onWayTime) {
        this.onWayTime = onWayTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(String priceMin) {
        this.priceMin = priceMin;
    }

    public String getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(String priceMax) {
        this.priceMax = priceMax;
    }

    @Override
    public String toString() {
        return "BusRoute{" +
                "id='" + id + '\'' +
                ", departureCity='" + departureCity + '\'' +
                ", arrivalCity='" + arrivalCity + '\'' +
                ", departureDate='" + departureDate + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", onWayTime='" + onWayTime + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                ", priceMin='" + priceMin + '\'' +
                ", priceMax='" + priceMax + '\'' +
                '}';
    }
}
