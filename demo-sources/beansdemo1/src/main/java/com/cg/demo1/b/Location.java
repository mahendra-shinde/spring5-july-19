package com.cg.demo1.b;

public class Location {
	private String address;
	private String city;
	
	@Override
	public String toString() {
		return "Location [address=" + address + ", city=" + city + "]";
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
}
