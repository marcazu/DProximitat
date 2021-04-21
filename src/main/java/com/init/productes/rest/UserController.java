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

import com.init.productes.entity.User;
import com.init.productes.services.UserService;


@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping // get all users
	public ResponseEntity<List<User>> getUsers(){
		return ResponseEntity.ok(userService.getUsers());
	}
	@RequestMapping(value ="{userId}") // get user concret
	public ResponseEntity<User>getUserById(@PathVariable("userId")Long userId){
		 return ResponseEntity.ok(userService.getUser(userId));
	}
	
	@DeleteMapping(value ="{userId}") // delete user
	public ResponseEntity<String> deleteUser(@PathVariable("userId")Long userId){
		userService.deleteUser(userId);
		String response = "s'ha eliminat el user amb ID: " + userId;
		return ResponseEntity.ok(response);
	}
	
	@PutMapping // modificar user
	public ResponseEntity<String> updateUser(@RequestBody User user){
		userService.updateUser(user);
		String response = "s'ha modificat el user amb ID: " + user.getId();
		return ResponseEntity.ok(response);  

	}
	
	@PostMapping
	public ResponseEntity<String> createProducte(@RequestBody User user){
		userService.crearUser(user);
		String response = "s'ha creat el user amb ID: " + user.getId();
		return ResponseEntity.ok(response);
	}
	
	@RequestMapping(value ="/{userId}/addBotiga/{botigaId}", method =RequestMethod.PUT)
	public ResponseEntity<String>addBotiga(@PathVariable("userId")Long userId, @PathVariable("botigaId")Long botigaId) {
		userService.linkarBotiga(userId,botigaId);
		String response = "S'ha afegit la botiga amb Id: " + botigaId + "al usuari amb Id: " +userId;
		return ResponseEntity.ok(response);
		
	}	
	
	@RequestMapping(value="/{userId}/addCarro/{producteId}", method = RequestMethod.PUT)
	public ResponseEntity<String>addProducteCarro(@PathVariable("userId") Long userId, @PathVariable("producteId") Long producteId){
		userService.afegirProducteCarro(userId,producteId);
		String response = "S'ha afegit el producte amb Id: " + producteId + "al usuari amb Id: " +userId;
		return ResponseEntity.ok(response);
	}
}
