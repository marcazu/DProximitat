package com.init.productes.rest;

import java.util.List;

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
	//@GetMapping
	@RequestMapping(value= "hello", method=RequestMethod.GET)
	public String hello() {
		return "Hello world";
	}*/
	@GetMapping
	public ResponseEntity<List<Product>> getProduct(){
		Product product = new Product();
		product.setId(1l);
		product.setName("producto1");
		product.setDescripció("");
		product.setTipus("");
		return ResponseEntity.ok(product);
	}

}
