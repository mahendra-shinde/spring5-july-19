package com.cg.demo1.b;

public class Department {
	private String name;
	private Location location;
	
	
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
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	
	
}
