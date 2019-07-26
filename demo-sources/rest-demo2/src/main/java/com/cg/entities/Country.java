package com.cg.entities;

public class Country {
	private String code;
	private String name;
	private String continent;
	private String capital;
	

	public Country() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Country(String code, String name, String continent, String capital) {
		super();
		this.code = code;
		this.name = name;
		this.continent = continent;
		this.capital = capital;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContinent() {
		return continent;
	}
	public void setContinent(String continent) {
		this.continent = continent;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	
	
	
}
