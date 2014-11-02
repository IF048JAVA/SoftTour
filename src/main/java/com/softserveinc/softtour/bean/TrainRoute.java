package com.softserveinc.softtour.bean;

/**
 * @author Andrii
 * Contains information about a route of train
 */
public class TrainRoute {

	//It's a number of the train
	private String id;
	private String departureCity;
	private String arrivalCity;
	private String departureDate;
	private String departureTime;
	private String onWayTime;
	private String arrivalTime;
	private String priceMin;
	private String priceMax;

	public TrainRoute() {
	}

	public TrainRoute(String id, String departureCity, String arrivalCity,
			String departureDate, String departureTime, String onWayTime,
			String arrivalTime, String priceMin, String priceMax) {
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((arrivalCity == null) ? 0 : arrivalCity.hashCode());
		result = prime * result
				+ ((arrivalTime == null) ? 0 : arrivalTime.hashCode());
		result = prime * result
				+ ((departureCity == null) ? 0 : departureCity.hashCode());
		result = prime * result
				+ ((departureDate == null) ? 0 : departureDate.hashCode());
		result = prime * result
				+ ((departureTime == null) ? 0 : departureTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((onWayTime == null) ? 0 : onWayTime.hashCode());
		result = prime * result
				+ ((priceMax == null) ? 0 : priceMax.hashCode());
		result = prime * result
				+ ((priceMin == null) ? 0 : priceMin.hashCode());
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
		TrainRoute other = (TrainRoute) obj;
		if (arrivalCity == null) {
			if (other.arrivalCity != null)
				return false;
		} else if (!arrivalCity.equals(other.arrivalCity))
			return false;
		if (arrivalTime == null) {
			if (other.arrivalTime != null)
				return false;
		} else if (!arrivalTime.equals(other.arrivalTime))
			return false;
		if (departureCity == null) {
			if (other.departureCity != null)
				return false;
		} else if (!departureCity.equals(other.departureCity))
			return false;
		if (departureDate == null) {
			if (other.departureDate != null)
				return false;
		} else if (!departureDate.equals(other.departureDate))
			return false;
		if (departureTime == null) {
			if (other.departureTime != null)
				return false;
		} else if (!departureTime.equals(other.departureTime))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (onWayTime == null) {
			if (other.onWayTime != null)
				return false;
		} else if (!onWayTime.equals(other.onWayTime))
			return false;
		if (priceMax == null) {
			if (other.priceMax != null)
				return false;
		} else if (!priceMax.equals(other.priceMax))
			return false;
		if (priceMin == null) {
			if (other.priceMin != null)
				return false;
		} else if (!priceMin.equals(other.priceMin))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TrainRoute [id=" + id + ", departureCity=" + departureCity
				+ ", arrivalCity=" + arrivalCity + ", departureDate="
				+ departureDate + ", departureTime=" + departureTime
				+ ", onWayTime=" + onWayTime + ", arrivalTime=" + arrivalTime
				+ ", priceMin=" + priceMin + ", priceMax=" + priceMax + "]";
	}

}