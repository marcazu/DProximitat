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

import com.init.productes.entity.Botiga;
import com.init.productes.entity.Producte;
import com.init.productes.repository.BotiguesRepository;
import com.init.productes.repository.ProductesRespository;

@RestController
@RequestMapping("/botigues")
public class BotigaController {
	
	@Autowired
	private BotiguesRepository botiguesRepository;
	
	@Autowired
	private ProductesRespository productesRepository;

	@GetMapping // get all botigues
	public ResponseEntity<List<Botiga>> getBotiga(){
		List<Botiga> botigues = botiguesRepository.findAll();
		return ResponseEntity.ok(botigues);	
	}
	
	@RequestMapping(value ="{botigaId}") // get botiga concreta
	public ResponseEntity<Botiga>getBotigaById(@PathVariable("botigaId")Long botigaId){
		Optional<Botiga> optionalBotiga = botiguesRepository.findById(botigaId);		
		if(optionalBotiga.isPresent()) return ResponseEntity.ok(optionalBotiga.get()); 
		else return ResponseEntity.noContent().build();// no troba el objecte
	}
	
	@PostMapping // post botiga
	public ResponseEntity<Botiga> createBotiga(@RequestBody Botiga botiga){
		Botiga newBotiga = botiguesRepository.save(botiga);
		return ResponseEntity.ok(newBotiga);
	}
	
	@DeleteMapping(value ="{botigaId}") // delete botiga
	public ResponseEntity<Void> deleteBotiga(@PathVariable("botigaId")Long botigaId){
		botiguesRepository.deleteById(botigaId);
		return ResponseEntity.ok(null);
	}
	
	@PutMapping // modificar botiga
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
	//
	//afegir un producte a la botiga
	@RequestMapping(value ="/{botigaId}/addProducte/{productId}", method =RequestMethod.PUT)
	public ResponseEntity<List<Producte>>AddProducte(@PathVariable("botigaId")Long botigaId, @PathVariable("productId")Long productId) {
		Optional<Botiga> optionalBotiga = botiguesRepository.findById(botigaId);//optenir botiga
		Optional<Producte> optionalProducte = productesRepository.findById(productId); //obtenir botiga
		
		if (optionalBotiga.isPresent()) {
			Botiga botiga = optionalBotiga.get(); //optenim la botiga
			if(optionalProducte.isPresent()) {
				Producte producte = optionalProducte.get();
				botiga.addProducteBotiga(producte);
				botiguesRepository.save(botiga);
				
				return ResponseEntity.ok(botiga.getProductesBotiga());			
			}
			else {
				//falta tractar error millor 
				return ResponseEntity.noContent().build();
			}
		}
		else {
			return ResponseEntity.noContent().build();
			//Botiga no existeix, falta tractar millor els errors
		}
		
	}
	@RequestMapping(value = "/{botigaId}/productes", method = RequestMethod.GET)
	public ResponseEntity<List<Producte>> getProductesBotiga(@PathVariable("botigaId")Long botigaId) {
		Optional<Botiga> optionalBotiga = botiguesRepository.findById(botigaId);//optenir botiga
		if (optionalBotiga.isPresent()) {
			Botiga botiga = optionalBotiga.get(); //optenim la botiga
			return ResponseEntity.ok(botiga.getProductesBotiga());
		}
		else return ResponseEntity.noContent().build();
	}	
	
	
}
