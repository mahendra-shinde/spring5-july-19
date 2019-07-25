package com.cg.doa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.cg.entities.Product;


@Repository
public class ProductDAOImpl implements ProductDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void save(Product p) {
		em.persist(p);
		em.flush();
	}

	@Override
	public Product findById(Integer productId) {
		return em.find(Product.class, productId);
	}

	@Override
	public List<Product> findAll() {
		Query q = em.createQuery("from Product p");//That's "JPQL" not SQL !!!
		return q.getResultList();
	}

}
