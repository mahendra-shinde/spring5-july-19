package com.cg.demo1.a;

public class Department {
	private String name;
	private String location;
	
	
	@Override
	public String toString() {
		return "Department [name=" + name + ", location=" + location + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	
}
