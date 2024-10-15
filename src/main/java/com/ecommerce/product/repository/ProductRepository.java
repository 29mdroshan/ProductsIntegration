package com.ecommerce.product.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ecommerce.product.model.Products;

public interface ProductRepository extends MongoRepository<Products, Integer> {

	Products findByProductId(int product_id);

}
