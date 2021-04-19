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

import com.init.productes.entity.Botiga;
import com.init.productes.entity.Producte;
import com.init.productes.services.BotigaService;

@RestController
@RequestMapping("/botigues")
public class BotigaController {
	
	@Autowired
	private BotigaService botigaService;

	@GetMapping // get all botigues
	public ResponseEntity<List<Botiga>> getBotiga(){
		System.out.println("Hello, logs!");
		return ResponseEntity.ok(botigaService.getbotigues());	
	}
	
	@RequestMapping(value ="{botigaId}") // get botiga concreta
	public ResponseEntity<Botiga>getBotigaById(@PathVariable("botigaId")Long botigaId){
		if(botigaService.getBotigaById(botigaId) != null) return ResponseEntity.ok(botigaService.getBotigaById(botigaId));
		else return ResponseEntity.noContent().build();// no troba el objecte
	}
	
	@PostMapping // post botiga
	public ResponseEntity<Botiga> createBotiga(@RequestBody Botiga botiga){
		botigaService.creteBotiga(botiga);
		return ResponseEntity.ok(botiga);
	}
	
	@DeleteMapping(value ="{botigaId}") // delete botiga
	public ResponseEntity<Void> deleteBotiga(@PathVariable("botigaId")Long botigaId){
		botigaService.deleteById(botigaId);
		return ResponseEntity.ok(null);
	}
	
	@PutMapping // modificar botiga
	public ResponseEntity<Botiga> updateBotiga(@RequestBody Botiga botiga){
		int response = botigaService.updateBotiga(botiga);
		if (response == 1)return ResponseEntity.ok().build();  
		else return ResponseEntity.notFound().build();// no troba el objecte
	}
	//
	//afegir un producte a la botiga
	@RequestMapping(value ="/{botigaId}/addProducte/{productId}", method =RequestMethod.PUT)
	public ResponseEntity<Void>AddProducte(@PathVariable("botigaId")Long botigaId, @PathVariable("productId")Long productId) {
		int response = botigaService.afegirProducte(botigaId, productId);
		if (response == 1)return ResponseEntity.ok().build();
		else return ResponseEntity.noContent().build();

	}
	@RequestMapping(value = "/{botigaId}/productes", method = RequestMethod.GET)
	public ResponseEntity<List<Producte>> getProductesBotiga(@PathVariable("botigaId")Long botigaId) {
		return ResponseEntity.ok(botigaService.getProductesBotiga(botigaId));
	}
}
