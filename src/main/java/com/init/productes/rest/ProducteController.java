package com.init.productes.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.init.productes.entity.Producte;

@RestController
@RequestMapping("/productes")
public class ProducteController {
	

	
	@GetMapping
	public ResponseEntity<Producte> sgetProduct(){
		Producte producte = new Producte();
		producte.setId(1);
		producte.setNom("nom");
		producte.setDescripció("descripcio");
		producte.setTipus("tipus1");
		return ResponseEntity.ok(producte);
		
		
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
