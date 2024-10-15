package com.ecommerce.product.service;

import java.util.List;

import com.ecommerce.product.model.Products;

public interface ProductService {

	public Products saveProduct(Products product);
	
	public Products findByProductId(int product_id);
	
	public void deleteProduct(int product_id);
	
	public List<Products> getAllProducts();
}
