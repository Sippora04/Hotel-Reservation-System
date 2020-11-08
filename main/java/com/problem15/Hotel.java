package com.problem15;

public class Hotel {
	private String hotelName;
	private int regularCustomerRate;
	
	public Hotel(int regularCustomerRate ,String hotelName) {
		super();
		this.regularCustomerRate = regularCustomerRate;
		this.hotelName = hotelName;
	}
	
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public int getRegularCustomerRate() {
		return regularCustomerRate;
	}
	public void setRegularCustomerRate(int regularCustomerRate) {
		this.regularCustomerRate = regularCustomerRate;
	}
	@Override
	public String toString() {
		return "Hotel Name: " + hotelName + ", Regular Rate: $" + regularCustomerRate;
	}
}