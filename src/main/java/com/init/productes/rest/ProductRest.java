package com.init.productes.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.init.productes.entity.Product;

@RestController
@RequestMapping("/products")
public class ProductRest {
 
	/*
	@GetMapping
	public ResponseEntity<Product> sgetProduct(){
		Product product = new Product();
		product.setId(1L);
		product.setName("holi");
		return ResponseEntity.ok(product);
		
		
	}*/

	@GetMapping
	@RequestMapping(value= "hello", method=RequestMethod.GET)
	public String hello() {
		return "Hello world";
	}
	/*
	@GetMapping
	public ResponseEntity<List<Product>> getProduct(){

		return null;
		//return ResponseEntity.ok(product);
	}*/

}
