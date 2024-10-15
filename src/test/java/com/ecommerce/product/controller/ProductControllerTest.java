package com.ecommerce.product.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.ecommerce.product.model.Products;
import com.ecommerce.product.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


@WebMvcTest
class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProductService service;
	
	
	ObjectMapper mapper = new ObjectMapper();
	ObjectWriter writer = mapper.writer();
	
	List<Products> list = new ArrayList<>();
	
	@BeforeEach
	public void setUp() {
		list.add(new Products(1,"Shirt","Clothing",750,"Campus Checks pattern shirt"));
		list.add(new Products(2,"TShirt","Clothing",4000,"Radster tshirt"));
		list.add(new Products(3,"Reslme 8","Electronics",8000,"relme 8 mobline phone with 5000mah battery"));

		Collections.sort(list,(p1,p2)->p1.getProductId()-p2.getProductId());
	}
	
	@Order(value=1)
	@Test
	void testSaveProduct() throws Exception {
		Products product1=new Products(1,"Shirt","Clothing",750,"Campus Checks pattern shirt");
		
		String productAsString = writer.writeValueAsString(product1);
		
		when(service.saveProduct(any(Products.class))).thenReturn(product1);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/ecommerse/product").contentType(MediaType.APPLICATION_JSON).content(productAsString))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.content().string(productAsString))
		.andExpect(jsonPath("$.productId", Matchers.is(product1.getProductId())))
		.andExpect(jsonPath("$.productName", Matchers.is(product1.getProductName())))
		.andExpect(jsonPath("$.productPrice", Matchers.is(product1.getProductPrice())));
		
	}

	@Test
	void testFindByProductId() throws Exception {
		Products product1=new Products(1,"Shirt","Clothing",750,"Campus Checks pattern shirt");
	
		when(service.findByProductId(1)).thenReturn(product1);
		
		
		mockMvc.perform(MockMvcRequestBuilders
				.get("/ecommerse/product/{id}", 1)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isNotEmpty())
				.andExpect(jsonPath("$.productName").value(product1.getProductName()))
				.andExpect(jsonPath("$.productPrice",Matchers.is(product1.getProductPrice())));
	}

	@Test
	void testDeleteProduct() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/ecommerse/product/{product_id}",1)
				.accept(MediaType.APPLICATION_JSON))
            	.andExpect(status().isOk());
		
	
	}

	@Test
	void testGetAllProducts() throws Exception {
		
		when(service.getAllProducts()).thenReturn(list);
		mockMvc.perform(MockMvcRequestBuilders.get("/ecommerse/product")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(3)))
				.andExpect(jsonPath("$[0].productId", Matchers.is(list.get(0).getProductId())))
				.andExpect(jsonPath("$[2].productName").value(list.get(2).getProductName()))
				.andExpect(jsonPath("$[1].productPrice", Matchers.is(list.get(1).getProductPrice())));
	}

}
