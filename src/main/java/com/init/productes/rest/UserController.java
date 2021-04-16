package com.init.productes.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.init.productes.entity.Botiga;
import com.init.productes.entity.User;
import com.init.productes.repository.UserRepository;


@RestController
@RequestMapping("/Users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping // get all users
	public ResponseEntity<List<User>> getUsers(){
		List<User> users = userRepository.findAll();
		return ResponseEntity.ok(users);	
	}
	@RequestMapping(value ="{userId}") // get user concret
	public ResponseEntity<User>getUserById(@PathVariable("userId")Long userId){
		Optional<User> optionalUser = userRepository.findById(userId);		
		if(optionalUser.isPresent()) return ResponseEntity.ok(optionalUser.get()); 
		else return ResponseEntity.noContent().build();// no troba el objecte
	}
	
	@DeleteMapping(value ="{userId}") // delete user
	public ResponseEntity<Void> deleteUser(@PathVariable("userId")Long userId){
		userRepository.deleteById(userId);
		return ResponseEntity.ok(null);
	}
	
	@PutMapping // modificar user
	public ResponseEntity<User> updateUser(@RequestBody User user){
		Optional<User> optionalUser = userRepository.findById(user.getId());		
		if(optionalUser.isPresent()) {
			User updateUser = optionalUser.get();
			updateUser.setNom(user.getNom());
			updateUser.setEmail(user.getEmail());
			updateUser.setTelefon(user.getTelefon());
			updateUser.setEsBotiguer(user.getEsBotiguer());
			updateUser.setBotiguesUsuari(user.getBotiguesUsuari());
			userRepository.save(updateUser);
			return ResponseEntity.ok(updateUser);  
		}
		else return ResponseEntity.notFound().build();// no troba el objecte
	}
}
