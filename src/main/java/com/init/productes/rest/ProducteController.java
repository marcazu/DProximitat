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


@RestController
@RequestMapping("/productes")
public class ProducteController {
	
	@Autowired
	private ProductesRespository productRepository;
	
	@GetMapping
	public ResponseEntity<List<Producte>> getProduct(){
		List<Producte> productes = productRepository.findAll();
		return ResponseEntity.ok(productes);	
	}
	
	@RequestMapping(value ="{productId}")
	public ResponseEntity<Producte>getProductById(@PathVariable("productId")Long productId){
		Optional<Producte> optionalProduct = productRepository.findById(productId);		
		if(optionalProduct.isPresent()) return ResponseEntity.ok(optionalProduct.get()); 
		else return ResponseEntity.noContent().build();// no troba el objecte
	}
	@PostMapping
	public ResponseEntity<Producte> createProducte(@RequestBody Producte producte){
		Producte newProducte = productRepository.save(producte);
		return ResponseEntity.ok(newProducte);
	}
	
	@DeleteMapping(value ="{productId}")
	public ResponseEntity<Void> deleteProducte(@PathVariable("productId")Long productId){
		productRepository.deleteById(productId);
		return ResponseEntity.ok(null);
	}
		
	@PutMapping
	public ResponseEntity<Producte> updateProducte(@RequestBody Producte producte){
		Optional<Producte> optionalProduct = productRepository.findById(producte.getId());		
		if(optionalProduct.isPresent()) {
			Producte updateProducte = optionalProduct.get();
			updateProducte.setNom(producte.getNom());
			updateProducte.setDescripció(producte.getDescripció());
			updateProducte.setTipus(producte.getTipus());
			productRepository.save(updateProducte);
			return ResponseEntity.ok(updateProducte);  
		}
		else return ResponseEntity.notFound().build();// no troba el objecte
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
