package com.cg.doa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entities.Product;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer>{
	
}
