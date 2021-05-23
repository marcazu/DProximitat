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

import com.init.productes.Dto.BotigaDto;
import com.init.productes.Dto.ComandaDto;
import com.init.productes.Dto.ProducteDto;
import com.init.productes.Dto.ProducteIdQuantitatDto;
import com.init.productes.Dto.UserDto;
import com.init.productes.entity.Botiga;
import com.init.productes.entity.Comanda;
import com.init.productes.entity.Producte;
import com.init.productes.services.ComandaService;

@RestController
@RequestMapping("/comandes")
public class ComandaController {
	
	@Autowired
	private ComandaService comandaService;
	
	private String exceptionString;
	
	
	@RequestMapping(method =RequestMethod.GET)
	public ResponseEntity<List<ComandaDto>> getComandes(){
		return ResponseEntity.ok(comandaService.getComandes());	
	}
	
	@RequestMapping(value ="{comandaId}",method =RequestMethod.GET) // get comanda concreta by id
	public ResponseEntity<ComandaDto>getcomandaById(@PathVariable("comandaId")Long comandaId){
		
		return ResponseEntity.ok(comandaService.getProducteById(comandaId));
	}
	
	@RequestMapping(method =RequestMethod.POST)
	public ResponseEntity<String> createBotiga(@RequestBody Comanda comanda){
		comandaService.createComanda(comanda);
		exceptionString ="S'ha creat la comanda amb Id: " + comanda.getId();
		return ResponseEntity.ok(exceptionString);
	}
	
	@RequestMapping(value="/{comandaId}/addProducte/{producteId}", method = RequestMethod.PUT)
	public ResponseEntity<String>addProducteCarro(@PathVariable("comandaId") Long comandaId, @PathVariable("producteId") Long producteId){
		comandaService.afegirProducte(comandaId,producteId);
		exceptionString = "S'ha afegit el producte amb Id: " + producteId + "al usuari amb Id: " +comandaId;
		return ResponseEntity.ok(exceptionString);
	}
	
	@RequestMapping(value="{comandaId}/propietari", method = RequestMethod.GET)
	public ResponseEntity<UserDto> getPropietariComanda(@PathVariable("comandaId")Long comandaId){
		return ResponseEntity.ok(comandaService.getPropietari(comandaId));	
	}
	
	@RequestMapping(value="{comandaId}/productes", method = RequestMethod.GET)
	public ResponseEntity<List<ProducteDto>> getProductesComandes(@PathVariable("comandaId")Long comandaId){
		return ResponseEntity.ok(comandaService.getProductescomanda(comandaId));	
	}
	@RequestMapping(value="{comandaId}/botiga",method = RequestMethod.GET)
	public ResponseEntity<BotigaDto> getBotigaComanda(@PathVariable("comandaId") Long comandaId){
		return ResponseEntity.ok(comandaService.getBotiga(comandaId));
		
	}
	@RequestMapping(value="{comandaId}/preparar", method = RequestMethod.PUT)
	public ResponseEntity<String> prepararComanda(@PathVariable("comandaId") Long comandaId){
		comandaService.prepararComanda(comandaId);
		exceptionString = "S'ha preparat la comanda: " + comandaId;
		return ResponseEntity.ok(exceptionString);
		
	}
	@RequestMapping(value="{comandaId}/entregar", method = RequestMethod.PUT)
	public ResponseEntity<String>entregarComanda(@PathVariable("comandaId") Long comandaId){
		comandaService.entregarComanda(comandaId);
		exceptionString = "S'ha entregat la comanda: " + comandaId;
		return ResponseEntity.ok(exceptionString);
	}
	@RequestMapping(value="{comandaId}/quantitat",method = RequestMethod.GET)
	public ResponseEntity<List<String>> getQuantitats(@PathVariable("comandaId") Long comandaId){
		return ResponseEntity.ok(comandaService.getQuantitats(comandaId));
	}
	@RequestMapping(value="{comandaId}/addProductes",method = RequestMethod.PUT)
	public ResponseEntity<String> putQuantitats(@PathVariable("comandaId") Long comandaId, @RequestBody List<ProducteIdQuantitatDto> producteQuantitatDtoList){
		comandaService.afegirProducteQuantitat(comandaId,producteQuantitatDtoList);
		exceptionString = "s'ha afegit els productes a la comanda " + comandaId;
		return ResponseEntity.ok(exceptionString);
	}
}
