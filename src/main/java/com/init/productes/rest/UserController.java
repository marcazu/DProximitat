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

import com.init.productes.Dto.ComandaDto;
import com.init.productes.Dto.ProducteDto;
import com.init.productes.Dto.UserDto;
import com.init.productes.entity.User;
import com.init.productes.services.UserService;


@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	private String exceptionResponse;
	
	@GetMapping // get all users
	public ResponseEntity<List<UserDto>> getUsers(){
		return ResponseEntity.ok(userService.getUsers());
	}
	@RequestMapping(value ="{userId}") // get user concret
	public ResponseEntity<UserDto>getUserById(@PathVariable("userId")Long userId){
		 return ResponseEntity.ok(userService.getUser(userId));
	}
	
	@DeleteMapping(value ="{userId}") // delete user
	public ResponseEntity<String> deleteUser(@PathVariable("userId")Long userId){
		userService.deleteUser(userId);
		exceptionResponse = "s'ha eliminat el user amb ID: " + userId;
		return ResponseEntity.ok(exceptionResponse);
	}
	
	@PutMapping // modificar user
	public ResponseEntity<String> updateUser(@RequestBody User user){
		userService.updateUser(user);
		exceptionResponse = "s'ha modificat el user amb ID: " + user.getId();
		return ResponseEntity.ok(exceptionResponse);  

	}
	
	@PostMapping
	public ResponseEntity<String> createProducte(@RequestBody User user){
		userService.crearUser(user);
		exceptionResponse = "s'ha creat el user amb ID: " + user.getId();
		return ResponseEntity.ok(exceptionResponse);
	}
	
	@RequestMapping(value ="/{userId}/addBotiga/{botigaId}", method =RequestMethod.PUT)
	public ResponseEntity<String>addBotiga(@PathVariable("userId")Long userId, @PathVariable("botigaId")Long botigaId) {
		userService.linkarBotiga(userId,botigaId);
		exceptionResponse = "S'ha afegit la botiga amb Id: " + botigaId + "al usuari amb Id: " +userId;
		return ResponseEntity.ok(exceptionResponse);
		
	}	
	
	@RequestMapping(value="/{userId}/addCarro/{producteId}", method = RequestMethod.PUT)
	public ResponseEntity<String>addProducteCarro(@PathVariable("userId") Long userId, @PathVariable("producteId") Long producteId){
		userService.afegirProducteCarro(userId,producteId);
		exceptionResponse = "S'ha afegit el producte amb Id: " + producteId + "al usuari amb Id: " +userId;
		return ResponseEntity.ok(exceptionResponse);
	}
	
	@RequestMapping(value="/{userId}/deleteCarro/{producteId}", method = RequestMethod.PUT)
	public ResponseEntity<String>deleteProducteCarro(@PathVariable("userId") Long userId, @PathVariable("producteId") Long producteId){
		userService.deleteProducteCarro(userId,producteId);
		exceptionResponse = "S'ha esborrrat el producte amb Id: " + producteId + "al usuari amb Id: " +userId;
		return ResponseEntity.ok(exceptionResponse);
	}
	
	@RequestMapping(value="/{userId}/carro")
	public ResponseEntity<List<ProducteDto>> getcarro(@PathVariable("userId") Long userId){
		return ResponseEntity.ok(userService.getCarro(userId));
	}
	
	@RequestMapping(value="/{userId}/linkComanda/{comandaId}", method = RequestMethod.PUT)
	public ResponseEntity<String>linkarComanda(@PathVariable("userId") Long userId, @PathVariable("comandaId") Long comandaId){
		userService.linkcomanda(userId,comandaId);
		exceptionResponse = "S'ha linkat l'usuari amb Id: amb la comadna amb Id";
		return ResponseEntity.ok(exceptionResponse);		
	}
	
	@RequestMapping(value="/{userId}/comandes", method = RequestMethod.GET)
	public ResponseEntity<List<ComandaDto>>getComandesUser(@PathVariable("userID") Long userId){
		return ResponseEntity.ok(userService.getComandesUser(userId));
		
	}
	
	

}
