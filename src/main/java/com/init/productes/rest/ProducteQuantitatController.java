package com.init.productes.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.init.productes.Dto.ProducteQuantiatDto;
import com.init.productes.Dto.UserDto;
import com.init.productes.entity.Producte;
import com.init.productes.entity.ProducteQuantitat;
import com.init.productes.entity.User;
import com.init.productes.services.ProducteQuantitatService;
import com.init.productes.services.ProducteService;

@RestController
@RequestMapping("/pq")
public class ProducteQuantitatController {
	
	@Autowired
	private ProducteQuantitatService pqService;
	
	
	@RequestMapping(value = "/{comandaId}",method =RequestMethod.GET)
	// retorna els productes quantitats per la id de la comanda
	public ResponseEntity<List<ProducteQuantitat>> getPQ(@PathVariable("comandaId")Long comandaId){
		return ResponseEntity.ok(pqService.getByComandaId(comandaId));
		
	}
	@RequestMapping(method =RequestMethod.POST)
	// Crea un producteQuanitat amb la comandaId, el producteId i la quantitat
	public ResponseEntity<String>postPq(@RequestBody ProducteQuantiatDto pq){
		pqService.CrearProducteQuantiat(pq);
		return ResponseEntity.ok("s'ha creat un nou producte quantitat");
	}
	
	@RequestMapping(value ="/{comandaId}/{producteId}",method =RequestMethod.DELETE) // delete producteQuantitat
	public ResponseEntity<String> deleteUser(@PathVariable("comandaId")Long comandaId,@PathVariable("producteId")Long producteId){
		pqService.deletePQ(comandaId, producteId);
		return ResponseEntity.ok("S'ha eliminat el producteQuantiat");
	}
	
	@RequestMapping(value ="/{comandaId}/{producteId}",method =RequestMethod.PUT)
	public ResponseEntity<String> updateQuantiat(@PathVariable("comandaId")Long comandaId,@PathVariable("producteId")Long producteId,
			@RequestBody int quantitat){
		pqService.modificarQuantitat(comandaId,producteId,quantitat);
		return ResponseEntity.ok("s'ha modificat el producteQuantitat");  
	}


}
