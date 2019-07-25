package com.cg.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entities.Product;
import com.cg.services.ProductService;

@RestController
public class ProductController {
	
	@Autowired private ProductService service;
	
	@GetMapping("/")
	public String createSample() {
		Product p1 = new Product(101,"Windows 10 PRO","64Bit OS for Desktop & Laptops",8000D);
		Product p2 = new Product(102,"Ubuntu 18.04 Desktop","64Bit OS for Desktop & Laptops",0D);
		Product p3 = new Product(103,"Linux Mint 19","64Bit OS for Desktop & Laptops",0D);
		service.create(p1);
		service.create(p2);
		service.create(p3);
		return "Three products created!";
	}
	
	@GetMapping("/list")
	public List<Product> getAll(){
		return service.getAll();
	}
}
