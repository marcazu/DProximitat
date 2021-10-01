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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.init.productes.entity.Producte;
import com.init.productes.services.ProducteService;


@RestController
@RequestMapping("/productes")
public class ProducteController {
	
	@Autowired
	private ProducteService producteService;
	
	@RequestMapping(method =RequestMethod.GET)
	public ResponseEntity<List<Producte>> getProduct(){
		return ResponseEntity.ok(producteService.getProductes());	
	}
	
	@RequestMapping(value ="{productId}",method =RequestMethod.GET)
	public ResponseEntity<Producte>getProductById(@PathVariable("productId")Long productId){
		return ResponseEntity.ok(producteService.getProducteById(productId));
	}
	
	@RequestMapping(method =RequestMethod.POST)
	public ResponseEntity<String> createProducte(@RequestBody Producte producte){
		producteService.createProducte(producte);
		String result = "S'ha creat un producte amb id: " + producte.getId();
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(value ="{productId}", method =RequestMethod.DELETE)
	public ResponseEntity<String> deleteProducte(@PathVariable("productId")Long productId){
		producteService.deleteById(productId);
		String result = "S'ha eliminat el producte amb Id: "+ productId;
		return ResponseEntity.ok(result);
	}
		
	@RequestMapping(method =RequestMethod.PUT)
	public ResponseEntity<String> updateProducte(@RequestBody Producte producte){
		producteService.updateProducte(producte);
		String result = "S'ha modificat el producte amb Id: " + producte.getId();
		return ResponseEntity.ok(result);  
	}
	
	@RequestMapping(value ="{productId}/preu/{noupreu}",method =RequestMethod.PUT)
	public ResponseEntity<String> modificarPreuProducte(@PathVariable("productId")Long productId, @PathVariable("noupreu")Double noupreu){
		producteService.modificarPreu(productId,noupreu);
		String result = "S'ha modificat el preu del producte amb Id: " + productId;
		return ResponseEntity.ok(result);  
	}


}
