package com.cg.demo1.c;

import java.util.List;

public class Location {
	private String address;
	private String city;
	private List<Department> depts;
	
	@Override
	public String toString() {
		return "Location [address=" + address + ", city=" + city + ", depts=" + depts + "]";
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
	public List<Department> getDepts() {
		return depts;
	}
	public void setDepts(List<Department> depts) {
		this.depts = depts;
	}
	
}
