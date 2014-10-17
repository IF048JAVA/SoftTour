package com.softserveinc.softtour.dto;

public class TrainTransit {
	
	//FIXME It's number of train   comment 
	private String id;
    private String departureCity;
    private String arrivalCity;
    private String departureDate;
    private String departureTime;
    private String arrivalTime;
    private String priceMin;
    private String priceMax;
    
    public TrainTransit() {
    }
    
	public TrainTransit(String id, String departureCity, String arrivalCity,
			String departureDate, String departureTime, String arrivalTime,
			String priceFrom, String priceTo) {
		this.id = id;
		this.departureCity = departureCity;
		this.arrivalCity = arrivalCity;
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.priceMin = priceFrom;
		this.priceMax = priceTo;
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

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getPriceFrom() {
		return priceMin;
	}

	public void setPriceFrom(String priceFrom) {
		this.priceMin = priceFrom;
	}

	public String getPriceTo() {
		return priceMax;
	}

	public void setPriceTo(String priceTo) {
		this.priceMax = priceTo;
	}

	@Override
	public String toString() {
		return id + " " + departureCity + " - " + arrivalCity + " - " + departureDate + " " 
				+ departureTime + " - " + arrivalTime + " - " + priceMin + " - " + priceMax;
	}
}