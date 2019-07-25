package com.cg.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cg.doa.ProductDAO;
import com.cg.entities.Product;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired private ProductDAO dao;
	
	@Transactional(readOnly=true)
	@Override
	public Product find(int productId) {
		return dao.findById(productId);
	}

	@Transactional(readOnly=true)
	@Override
	public List<Product> getAll() {
		return dao.findAll();
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void create(Product p) {
		dao.save(p);
	}

}
