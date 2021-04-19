package com.init.productes.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.init.productes.entity.Producte;
import com.init.productes.repository.ProductesRespository;
import com.init.productes.services.ProducteService;


@RestController
@RequestMapping("/productes")
public class ProducteController {
	
	private ProducteService producteService;
	
	@GetMapping
	public ResponseEntity<List<Producte>> getProduct(){
		return ResponseEntity.ok(producteService.getProductes());	
	}
	
	@RequestMapping(value ="{productId}")
	public ResponseEntity<Producte>getProductById(@PathVariable("productId")Long productId){
		if(producteService.getProducteById(productId) != null) return ResponseEntity.ok(producteService.getProducteById(productId));
		else return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	public ResponseEntity<Producte> createProducte(@RequestBody Producte producte){
		producteService.createProducte(producte);
		return ResponseEntity.ok(producte);
	}
	
	@DeleteMapping(value ="{productId}")
	public ResponseEntity<Void> deleteProducte(@PathVariable("productId")Long productId){
		producteService.deleteById(productId);
		return ResponseEntity.ok(null);
	}
		
	@PutMapping
	public ResponseEntity<Void> updateProducte(@RequestBody Producte producte){
		int response = producteService.updateProducte(producte);
		if (response == 1)return ResponseEntity.ok().build();  
		else return ResponseEntity.notFound().build();// no troba el objecte
	}


}
