package com.init.productes.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.init.productes.entity.Producte;
import com.init.productes.services.ProducteService;


@RestController
@RequestMapping("/productes")
public class ProducteController {
	
	@Autowired
	private ProducteService producteService;
	
	@GetMapping
	public ResponseEntity<List<Producte>> getProduct(){
		return ResponseEntity.ok(producteService.getProductes());	
	}
	
	@RequestMapping(value ="{productId}")
	public ResponseEntity<Producte>getProductById(@PathVariable("productId")Long productId){
		return ResponseEntity.ok(producteService.getProducteById(productId));
	}
	
	@PostMapping
	public ResponseEntity<String> createProducte(@RequestBody Producte producte){
		producteService.createProducte(producte);
		String result = "S'ha creat un producte amb id: " + producte.getId();
		return ResponseEntity.ok(result);
	}
	
	@DeleteMapping(value ="{productId}")
	public ResponseEntity<String> deleteProducte(@PathVariable("productId")Long productId){
		producteService.deleteById(productId);
		String result = "S'ha eliminat el producte amb Id: "+ productId;
		return ResponseEntity.ok(result);
	}
		
	@PutMapping
	public ResponseEntity<String> updateProducte(@RequestBody Producte producte){
		producteService.updateProducte(producte);
		String result = "S'ha modificat el producte amb Id: " + producte.getId();
		return ResponseEntity.ok(result);  
	}


}
