package com.cg.doa;

import java.util.List;

import com.cg.entities.Product;

public interface ProductDAO {
	public void save(Product p);
	public Product findById(Integer productId);
	public List<Product> findAll();
}
