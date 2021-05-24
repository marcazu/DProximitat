package com.init.productes.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.init.productes.Dto.UserDto;
import com.init.productes.entity.Producte;
import com.init.productes.entity.ProducteQuantitat;
import com.init.productes.entity.User;
import com.init.productes.services.ProducteQuantitatService;
import com.init.productes.services.ProducteService;

@RestController
@RequestMapping("/producteQuantitat")
public class ProducteQuantitatController {
	
	@Autowired
	private ProducteQuantitatService pqService;
	
	@RequestMapping(method =RequestMethod.GET)
	public ResponseEntity<List<ProducteQuantitat>> getPQ(){
		return ResponseEntity.ok(pqService.getAll());
		
	}
	@RequestMapping(method =RequestMethod.POST)
	public ResponseEntity<String>postPq(@RequestBody ProducteQuantitat pq){
		pqService.crearPq(pq);
		return ResponseEntity.ok("s'ha creat un nou producte quantitat");
	}
	
	@RequestMapping(value ="/{pqId}",method =RequestMethod.DELETE) // delete user
	public ResponseEntity<String> deleteUser(@PathVariable("pqId")Long pqId){
		pqService.deletePQ(pqId);
		return ResponseEntity.ok("S'ha eliminat el producteQuantiat");
	}
	
	@RequestMapping(method =RequestMethod.PUT)
	public ResponseEntity<String> updateUser(@RequestBody ProducteQuantitat pq){
		pqService.updatePQ(pq);
		return ResponseEntity.ok("s'ha modificat el producteQuantitat");  
	}
	@RequestMapping(value = "/{pqId}/linkarProducte/{producteId}",method =RequestMethod.PUT)
	public ResponseEntity<String>linkarProducte(@PathVariable("pqId")Long pqId,@PathVariable("producteId")Long producteId){
		pqService.linkarProducte(pqId,producteId);
		return ResponseEntity.ok("S'ha linkat correctament el producteQuantitat amb el producte");
	}

}
