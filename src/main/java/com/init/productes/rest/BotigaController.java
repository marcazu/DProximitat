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
import org.springframework.web.bind.annotation.RestController;

import com.init.productes.entity.Botiga;
import com.init.productes.entity.Producte;
import com.init.productes.repository.BotiguesRepository;

@RestController
@RequestMapping("/botigues")
public class BotigaController {
	
	@Autowired
	private BotiguesRepository botiguesRepository;

	@GetMapping
	public ResponseEntity<List<Botiga>> getBotiga(){
		List<Botiga> botigues = botiguesRepository.findAll();
		return ResponseEntity.ok(botigues);	
	}
	
	@RequestMapping(value ="{botigaId}")
	public ResponseEntity<Botiga>getBotigaById(@PathVariable("botigaId")Long botigaId){
		Optional<Botiga> optionalBotiga = botiguesRepository.findById(botigaId);		
		if(optionalBotiga.isPresent()) return ResponseEntity.ok(optionalBotiga.get()); 
		else return ResponseEntity.noContent().build();// no troba el objecte
	}
	
	@PostMapping
	public ResponseEntity<Botiga> createBotiga(@RequestBody Botiga botiga){
		Botiga newBotiga = botiguesRepository.save(botiga);
		return ResponseEntity.ok(newBotiga);
	}
	
	@DeleteMapping(value ="{botigaId}")
	public ResponseEntity<Void> deleteBotiga(@PathVariable("botigaId")Long botigaId){
		botiguesRepository.deleteById(botigaId);
		return ResponseEntity.ok(null);
	}
	
	@PutMapping
	public ResponseEntity<Botiga> updateBotiga(@RequestBody Botiga botiga){
		Optional<Botiga> optionalBotiga = botiguesRepository.findById(botiga.getId());		
		if(optionalBotiga.isPresent()) {
			Botiga updateBotiga = optionalBotiga.get();
			updateBotiga.setNom(botiga.getNom());
			updateBotiga.setDescripcio(botiga.getDescripcio());
			updateBotiga.setEmail(botiga.getEmail());
			updateBotiga.setLatitud(botiga.getLatitud());
			updateBotiga.setLongitud(botiga.getLongitud());
			updateBotiga.setTelefon(botiga.getTelefon());
			botiguesRepository.save(updateBotiga);
			return ResponseEntity.ok(updateBotiga);  
		}
		else return ResponseEntity.notFound().build();// no troba el objecte
	}
	
	
}
