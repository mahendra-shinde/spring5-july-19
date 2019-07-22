package com.cg.iocdemo1;

public class Address {
	private String line1, line2, city;
	
	//Create Getters/Setters
	//Create TWO Constructors
	//  1. Empty Constructor (With No Arguments)
	//  2. Parameterized Constructor

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Address(String line1, String line2, String city) {
		super();
		this.line1 = line1;
		this.line2 = line2;
		this.city = city;
	}

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
