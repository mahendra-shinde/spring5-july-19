package com.cg.controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entities.Product;

@RestController //RestController returns MODEL and NOT VIEW !!
public class HelloController {

	@GetMapping("/") // Home page
	public String hello() {
		return "Hello World";
	}
	
	
	// URL= http://localhost:3000/products
	@GetMapping("/products")
	public List<Product> getProducts(){
		List<Product> list = new LinkedList<Product>();
		list.add(new Product(101,"Windows 10 PRO","64Bit OS for Desktop & Laptops",8000D));
		list.add(new Product(102,"Ubuntu 18.04 Desktop","64Bit OS for Desktop & Laptops",0D));
		list.add(new Product(103,"Linux Mint 19","64Bit OS for Desktop & Laptops",0D));
		return list;
		
	}
}
