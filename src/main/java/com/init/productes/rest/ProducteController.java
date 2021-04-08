package com.init.productes.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.init.productes.entity.Producte;
import com.init.productes.repository.ProductesRespository;

@RestController
@RequestMapping("/productes")
public class ProducteController {
	
	@Autowired
	private ProductesRespository productRepository;
	
	@GetMapping
	public ResponseEntity<List<Producte>> sgetProduct(){
		List<Producte> productes = productRepository.findAll();
		return ResponseEntity.ok(productes);
		//return ResponseEntity.ok();
		
		
	}
	/*
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
