package com.cg.entities;

import javax.persistence.*;

@Entity
@Table(name="products")
public class Product {
	@Id
	private Integer productId;
	
	@Column(name="name",length=20)
	private String name;
	
	@Column(name="description",length=120)
	private String description;
	
	@Column(name="price")
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
