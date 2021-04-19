package com.init.productes.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.init.productes.entity.Botiga;
import com.init.productes.entity.Comanda;
import com.init.productes.services.ComandaService;

@RestController
@RequestMapping("/comandes")
public class ComandaController {
	
	@Autowired
	private ComandaService comandaService;
	
	
	@GetMapping // get all botigues
	public ResponseEntity<List<Comanda>> getComandes(){
		return ResponseEntity.ok(comandaService.getComandes());	
	}
	
	@RequestMapping(value ="{comandaId}") // get comanda concreta by id
	public ResponseEntity<Comanda>getcomandaById(@PathVariable("comandaId")Long comandaId){
		
		if(comandaService.getProducteById(comandaId) != null) return ResponseEntity.ok(comandaService.getProducteById(comandaId));
		else return ResponseEntity.noContent().build();
	}
	
	@PostMapping // post botiga
	public ResponseEntity<Comanda> createBotiga(@RequestBody Comanda comanda){
		comandaService.createComanda(comanda);
		return ResponseEntity.ok(comanda);
	}

}
