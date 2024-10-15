package com.ecommerce.product.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class Products {
	@Id
	private int productId;
	private String productName;
	private String productCategory;
	private int productPrice;
	private String productDesc;
	
	

}
