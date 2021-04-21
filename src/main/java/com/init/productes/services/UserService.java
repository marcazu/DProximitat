package com.init.productes.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.init.productes.entity.Botiga;
import com.init.productes.entity.Producte;
import com.init.productes.entity.User;
import com.init.productes.exception.ApiRequestException;
import com.init.productes.repository.BotiguesRepository;
import com.init.productes.repository.ProductesRespository;
import com.init.productes.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BotiguesRepository botigaRepository;
	@Autowired
	private ProductesRespository producteRepository;
	
	private String exceptionString;
	
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	public User getUser(Long userId) {
		Optional<User> optionalUser = userRepository.findById(userId);		
		if(optionalUser.isPresent()) return optionalUser.get();
		else {
			exceptionString = "No hi ha cap user amb ID:" + userId;
			throw new ApiRequestException(exceptionString);
		}
	}

	public void deleteUser(Long userId) {
		Optional<User> optionalUser = userRepository.findById(userId);		
		if(optionalUser.isPresent())userRepository.deleteById(userId);	 
		else {
			exceptionString = "No hi ha cap user amb ID:" + userId;
			throw new ApiRequestException(exceptionString);
		}
	}

	public void updateUser(User user) {
		Optional<User> optionalUser = userRepository.findById(user.getId());		
		if(optionalUser.isPresent()) {
			User updateUser = optionalUser.get();
			updateUser.setNom(user.getNom());
			updateUser.setEmail(user.getEmail());
			updateUser.setTelefon(user.getTelefon());
			updateUser.setEsBotiguer(user.getEsBotiguer());
			updateUser.setBotiguesUsuari(user.getBotiguesUsuari());
			userRepository.save(updateUser);
		}
		else {
			exceptionString = "No hi ha cap user amb ID:" + user.getId();
			throw new ApiRequestException(exceptionString);
		}
	}

	public void crearUser(User user) {
		userRepository.save(user);
		
	}

	public void linkarBotiga(Long userId, Long botigaId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		Optional<Botiga> optionalBotiga = botigaRepository.findById(botigaId);
		
		if(optionalUser.isPresent()) {
			if(optionalBotiga.isPresent()) {
				User user = optionalUser.get();
				Botiga botiga = optionalBotiga.get();
				user.addBotigaUser(botiga);
				userRepository.save(user);
			}
			else {
				exceptionString = "No hi ha cap botiga amb ID: " + botigaId;
				throw new ApiRequestException(exceptionString);
			}
		}
		else {
			exceptionString = "No hi ha cap user amb ID: " + userId;
			throw new ApiRequestException(exceptionString);
		}	
	}

	public void afegirProducteCarro(Long userId, Long producteId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		Optional<Producte> optionalProducte = producteRepository.findById(producteId);
		if(optionalUser.isPresent()) {
			if(optionalProducte.isPresent()) {
				User user = optionalUser.get();
				Producte producte = optionalProducte.get();
				user.addProducteCarro(producte);
				userRepository.save(user);		
			}
			else {
				exceptionString = "No hi ha cap producte amb ID: " + producteId;
				throw new ApiRequestException(exceptionString);
			}		
		}
		else {
			exceptionString = "No hi ha cap user amb ID: " + userId;
			throw new ApiRequestException(exceptionString);
		}	
	}
}
