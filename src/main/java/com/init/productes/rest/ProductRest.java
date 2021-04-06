package com.init.productes.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ProductRest {
<<<<<<< HEAD
 
	/*
	@GetMapping
	public ResponseEntity<Product> sgetProduct(){
		Product product = new Product();
		product.setId(1L);
		product.setName("holi");
		return ResponseEntity.ok(product);
		
		
	}*/

	@GetMapping
=======


	//@GetMapping
>>>>>>> parent of 98932a8 (producte fet)
	@RequestMapping(value= "hello", method=RequestMethod.GET)
	public String hello() {
		return "Hello world";
	}
<<<<<<< HEAD
=======
	
>>>>>>> parent of 98932a8 (producte fet)
	/*
	@GetMapping
	public ResponseEntity<List<Product>> getProduct(){

		return null;
		//return ResponseEntity.ok(product);
	}*/

}
