package com.init.productes.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.init.productes.entity.Comanda;
import com.init.productes.entity.Producte;
import com.init.productes.repository.ComandaRepository;

@RestController
@RequestMapping("/comandes")
public class coamandaController {
	
	private ComandaRepository comandaRepository;
	
	@GetMapping // get all botigues
	public ResponseEntity<List<Comanda>> getComandes(){
		List<Comanda> comandes = comandaRepository.findAll();
		return ResponseEntity.ok(comandes);	
	}
	
	@RequestMapping(value ="{comandaId}") // get comanda concreta by id
	public ResponseEntity<Comanda>getcomandaById(@PathVariable("comandaId")Long comandaId){
		Optional<Comanda> optionalComanda = comandaRepository.findById(comandaId);		
		if(optionalComanda.isPresent()) return ResponseEntity.ok(optionalComanda.get()); 
		else return ResponseEntity.noContent().build();// no troba el objecte
	}

}
