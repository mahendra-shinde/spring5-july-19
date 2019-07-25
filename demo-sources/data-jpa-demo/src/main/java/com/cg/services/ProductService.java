package com.cg.services;

import java.util.List;

import com.cg.entities.Product;

public interface ProductService {
	Product find(int productId);
	List<Product> getAll();
	void create(Product p);
}
