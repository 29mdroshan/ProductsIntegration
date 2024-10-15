package com.ecommerce.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.product.model.Products;
import com.ecommerce.product.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/ecommerse/product")
public class ProductController {

	@Autowired
	ProductService service;

	@PostMapping
	@Operation(summary = "Saving Product", responses = {
			@ApiResponse(description = "Product Detail Posted Sucessfully", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Products.class))),
			@ApiResponse(description = "product not saved", responseCode = "404", content = @Content(mediaType = "application/json")) })
	public Products saveProduct(@RequestBody Products product) {
		return service.saveProduct(product);
	}
	

	@GetMapping("/{product_id}")
	@Operation(summary = "Geting Product by ProductId", responses = {
			@ApiResponse(description = "Product detail", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Products.class))),
			@ApiResponse(description = "product not found", responseCode = "404", content = @Content) })
	public Products findByProductId(@PathVariable("product_id") int product_id) {
		return service.findByProductId(product_id);
	}
	

	@DeleteMapping("/{product_id}")
	@Operation(summary = "Deleting Product by ProductId", responses = {
			@ApiResponse(description = "Product detail", responseCode = "200", content = @Content(mediaType = "application/json")),
			@ApiResponse(description = "product not found", responseCode = "404", content = @Content) })
	public void deleteProduct(@PathVariable("product_id") int product_id) {
		service.deleteProduct(product_id);
	}
	

	@GetMapping
	@Operation(summary = "Get All Product", responses = {
			@ApiResponse(description = "Product Detail", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Products.class))),
			@ApiResponse(description = "Product List Not found", responseCode = "404", content = @Content) })
	public List<Products> getAllProducts() {
		return service.getAllProducts();
	}

}
