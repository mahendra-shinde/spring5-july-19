package com.cg.entities;

public class Product {

	private Integer productId;
	private String name;
	private String description;
	private Double price;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(Integer productId, String name, String description, Double price) {
		super();
		this.productId = productId;
		this.name = name;
		this.description = description;
		this.price = price;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
}
