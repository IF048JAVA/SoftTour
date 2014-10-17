package com.softserveinc.softtour.dto;

public class TrainTransit {

    private String departureCity;
    private String arrivalCity;
    private String departureTime;
    private String arrivalTime;
    private String priceFrom;
    private String priceTo;
    
    public TrainTransit() {
    }
    
	public TrainTransit(String departureCity, String arrivalCity,
			String departureTime, String arrivalTime, String priceFrom,
			String priceTo) {
		this.departureCity = departureCity;
		this.arrivalCity = arrivalCity;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.priceFrom = priceFrom;
		this.priceTo = priceTo;
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
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public String getPriceFrom() {
		return priceFrom;
	}
	public void setPriceFrom(String priceFrom) {
		this.priceFrom = priceFrom;
	}
	public String getPriceTo() {
		return priceTo;
	}
	public void setPriceTo(String priceTo) {
		this.priceTo = priceTo;
	}

	@Override
	public String toString() {
		return departureCity + " - " + arrivalCity + " - " + departureTime + " - " + arrivalTime 
				+ " - " + priceFrom + " - " + priceTo;
	}
	
}