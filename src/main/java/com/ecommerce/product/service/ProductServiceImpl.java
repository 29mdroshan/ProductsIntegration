package com.ecommerce.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.product.model.Products;
import com.ecommerce.product.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository repo;
	
	@Override
	public Products saveProduct(Products product) {
		
		return repo.save(product);
	}

	@Override
	public void deleteProduct(int product_id) {
		repo.deleteById(product_id);

	}

	@Override
	public List<Products> getAllProducts() {
		return repo.findAll();

	}

	@Override
	public Products findByProductId(int product_id) {
		return repo.findByProductId(product_id);
	}

}
