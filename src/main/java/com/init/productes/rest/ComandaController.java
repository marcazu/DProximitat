package com.init.productes.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		
		return ResponseEntity.ok(comandaService.getProducteById(comandaId));
	}
	
	@PostMapping // post botiga
	public ResponseEntity<String> createBotiga(@RequestBody Comanda comanda){
		comandaService.createComanda(comanda);
		String result ="S'ha creat la comanda amb Id: " + comanda.getId();
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(value="/{comandaId}/addProducte/{producteId}", method = RequestMethod.PUT)
	public ResponseEntity<String>addProducteCarro(@PathVariable("comandaId") Long comandaId, @PathVariable("producteId") Long producteId){
		comandaService.afegirProducte(comandaId,producteId);
		String response = "S'ha afegit el producte amb Id: " + producteId + "al usuari amb Id: " +comandaId;
		return ResponseEntity.ok(response);
	}

}
