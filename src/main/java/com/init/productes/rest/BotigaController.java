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

import com.init.productes.Dto.BotigaDto;
import com.init.productes.Dto.ComandaDto;
import com.init.productes.Dto.UserDto;
import com.init.productes.entity.Botiga;
import com.init.productes.entity.Producte;
import com.init.productes.services.BotigaService;

@RestController
@RequestMapping("/botigues")
public class BotigaController {
	
	@Autowired
	private BotigaService botigaService;

	@RequestMapping(method =RequestMethod.GET)// get all botigues
	public ResponseEntity<List<BotigaDto>> getBotiga(){
		System.out.println("Hello, logs!");
		return ResponseEntity.ok(botigaService.getbotigues());	
	}
	
	@RequestMapping(value ="{botigaId}", method =RequestMethod.GET ) // get botiga concreta
	public ResponseEntity<BotigaDto>getBotigaById(@PathVariable("botigaId")Long botigaId){
		return ResponseEntity.ok(botigaService.getBotigaById(botigaId));
		
	}
	
	@RequestMapping(method =RequestMethod.POST)
	public ResponseEntity<String> createBotiga(@RequestBody Botiga botiga){
		botigaService.creteBotiga(botiga);
		String result = "S'ha creat una botiga amb id: " + botiga.getId();
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(value ="{botigaId}",method =RequestMethod.DELETE)
	public ResponseEntity<String> deleteBotiga(@PathVariable("botigaId")Long botigaId){
		botigaService.deleteById(botigaId);
		String result = "S'ha eliminat el producte amb Id: "+ botigaId;
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(method =RequestMethod.PUT)
	public ResponseEntity<String> updateBotiga(@RequestBody Botiga botiga){
		botigaService.updateBotiga(botiga);
		String result = "S'ha modificat el producte amb Id: "+ botiga.getId();
		return ResponseEntity.ok(result);  

	}
	//afegir un producte a la botiga
	@RequestMapping(value ="/{botigaId}/addProducte/{productId}", method =RequestMethod.PUT)
	public ResponseEntity<String>AddProducte(@PathVariable("botigaId")Long botigaId, @PathVariable("productId")Long productId) {
		botigaService.afegirProducte(botigaId, productId);
		String result = "S'ha afegit el producte amb Id: "+ productId + " a la botiga amb Id: " + botigaId;
		return ResponseEntity.ok(result);

	}
	@RequestMapping(value = "/{botigaId}/productes", method = RequestMethod.GET)
	public ResponseEntity<List<Producte>> getProductesBotiga(@PathVariable("botigaId")Long botigaId) {
		return ResponseEntity.ok(botigaService.getProductesBotiga(botigaId));
	}
	
	@RequestMapping(value="/{botigaId}/propietari", method = RequestMethod.GET)
	public ResponseEntity<UserDto> getPropietari(@PathVariable("botigaId")Long botigaId){
		return ResponseEntity.ok(botigaService.getPropietari(botigaId));
		
	}
	@RequestMapping(value="/{botigaId}/addComanda/{comnadaId}", method = RequestMethod.PUT)
	public ResponseEntity<String>AddComanda(@PathVariable("botigaId")Long botigaId, @PathVariable("comandaId") Long comandaId){
		botigaService.afegirComanda(botigaId,comandaId);
		String result = "S'ha afegit la comanda amb Id: "+comandaId+" a la botiga amb Id: "+botigaId;
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(value="/{botigaId}/Comandes",method = RequestMethod.GET)
	public ResponseEntity<List<ComandaDto>>getComandes(@PathVariable("botigaId")Long botigaId){
		return ResponseEntity.ok(botigaService.getComandes(botigaId));
		
	}
}
