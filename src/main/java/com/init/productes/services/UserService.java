package com.init.productes.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.init.productes.Dto.BotigaDto;
import com.init.productes.Dto.ComandaDto;
import com.init.productes.Dto.ProducteDto;
import com.init.productes.Dto.UserDetailsRequestModelDto;
import com.init.productes.Dto.UserDto;
import com.init.productes.entity.Botiga;
import com.init.productes.entity.Comanda;
import com.init.productes.entity.NomTelefon;
import com.init.productes.entity.Producte;
import com.init.productes.entity.User;
import com.init.productes.exception.ApiRequestException;
import com.init.productes.repository.BotiguesRepository;
import com.init.productes.repository.ComandaRepository;
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
	@Autowired
	private ComandaRepository comandaRepository;
	
	private String exceptionString;
	
	public List<UserDto> getUsers() {
		//return userRepository.findAll();
		return userRepository.findAll().stream().map(user -> new UserDto(user)).collect(Collectors.toList());
		/*
		 * new
		List<UserDto> usersDto = new ArrayList<UserDto>();List<User> users = userRepository.findAll();
		for (User usuari : users) {
			UserDto userdto = new UserDto(usuari);
			usersDto.add(userdto);
		}
		return usersDto;
		*/
	}

	public UserDto getUser(Long userId) {
		
		UserDto userDto = new UserDto(obtenirUser(userId));
		return userDto;
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
		User updateUser = obtenirUser(user.getId());
		if(!user.getNom().isEmpty())updateUser.setNom(user.getNom());
		if(!user.getEmail().isEmpty())updateUser.setEmail(user.getEmail());
		if(!user.getTelefon().isEmpty())updateUser.setTelefon(user.getTelefon());
		if(!user.getIconaUrl().isEmpty())updateUser.setIconaUrl(user.getIconaUrl());
			//if(!user.getEsBotiguer().isBlank())updateUser.setEsBotiguer(user.getEsBotiguer());
		if(!user.getEmail().isEmpty())updateUser.setBotigaUsuari(user.getBotigaUsuari());
		userRepository.save(updateUser);
	}

	public void crearUser(User user) {
		user.setEsBotiguer(false);
		userRepository.save(user);	
	}

	public void linkarBotiga(Long userId, Long botigaId) {
		User user = obtenirUser(userId);
		Botiga botiga = obtenirBotiga(botigaId);
		user.setEsBotiguer(true);
		user.addBotigaUser(botiga);
		botiga.addBotiguer(user);
		userRepository.save(user);
	}

	//aquesta està per borrar
	public void afegirProducteCarro(Long userId, Long producteId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		Optional<Producte> optionalProducte = producteRepository.findById(producteId);
		if(optionalUser.isPresent()) {
			if(optionalProducte.isPresent()) {
				User user = obtenirUser(userId);
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

	//aquesta la borraré
	public void deleteProducteCarro(Long userId, Long producteId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		Optional<Producte> optionalProducte = producteRepository.findById(producteId);
		if(optionalUser.isPresent()) {
			if(optionalProducte.isPresent()) {
				User user = optionalUser.get();
				Producte producte = optionalProducte.get();
				List<Producte> carro = user.getCarro();
				for(Producte p : carro)	{
					if(p.getId() == producte.getId()) {
						carro.remove(p);
						userRepository.save(user);		
						return;
					}
				}
				// no ha trobaat l'objecte posar excepcio
				exceptionString = " no s'ha trobat el producte al carro del user";
				throw new ApiRequestException(exceptionString);
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

	public void linkcomanda(Long userId, Long comandaId) {
			User user = obtenirUser(userId);
			Comanda comanda = obtenirComanda(comandaId);
			comanda.setUserOwner(user);
			user.addComanda(comanda);//potserfalla aqui
			userRepository.save(user);
	}

	public List<ProducteDto> getCarro(Long userId) {
		User user = obtenirUser(userId);
		List<Producte> carro = user.getCarro();
		List<ProducteDto> carroDto = new ArrayList<ProducteDto>();
		for(Producte p :carro) {
			carroDto.add(new ProducteDto(p));
		}
		return carroDto;
	}

	public BotigaDto getBotigues(Long userId) {
		User user = obtenirUser(userId);
		Botiga botiga = user.getBotigaUsuari();
		return new BotigaDto(botiga);
		
	}


	public void modificarUserByDto(Long userId, UserDetailsRequestModelDto userDetailsRequestModel) {
		User updateUser = obtenirUser(userId);
		if(!userDetailsRequestModel.getEmail().isEmpty()) updateUser.setEmail(userDetailsRequestModel.getEmail());
		if(!userDetailsRequestModel.getNom().isEmpty())updateUser.setNom(userDetailsRequestModel.getNom());
		if(!userDetailsRequestModel.getTelefon().isEmpty())updateUser.setTelefon(userDetailsRequestModel.getTelefon());
		userRepository.save(updateUser);
	}
	
	public List<ComandaDto> getComandes(Long userId) {
		User user = obtenirUser(userId);
		List<Comanda> comandes = user.getComandesUsuari();
		List<ComandaDto> comandesDto = new ArrayList<ComandaDto>();
		for(Comanda c: comandes) {
			comandesDto.add(new ComandaDto(c));
		}
		if (comandesDto.isEmpty()){
			exceptionString = "no Hi ha coses ";
			throw new ApiRequestException(exceptionString);
		}
		else {
			System.out.println("retorno les comandesDto");	
		return comandesDto;}
	}

	public UserDto getUserFireBase(String userId) {
		UserDto userDto = new UserDto(obtenirGuidUser(userId));
		return userDto;
	}

	// obte les comandes d'un usuari a partir de la seva firebaseId
	public  List<ComandaDto> getComandesByFirebaseId(String firebaseId) {
		User user = obtenirGuidUser(firebaseId);
		List<Comanda> comandes = user.getComandesUsuari();
		List<ComandaDto> comandesDto = new ArrayList<ComandaDto>();
		for(Comanda c: comandes) {
			if(!c.getEntregada())comandesDto.add(new ComandaDto(c));
		}
		if (comandesDto.isEmpty()){
			exceptionString = "no Hi ha coses ";
			throw new ApiRequestException(exceptionString);
		}
		else {
			System.out.println("retorno les comandesDto");	
		return comandesDto;}
	}
	
	
	// FUNCIONS PER OBTENIR ENTITIES A PARTIR DE IDENTIFICADORS
	private User obtenirUser(Long userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		if(optionalUser.isPresent()) return optionalUser.get();
		String exceptionString = "No hi ha cap user amb ID: " + userId;
		throw new ApiRequestException(exceptionString);
	}
	
	private User obtenirGuidUser(String userGuId) {
		Optional<User> optionalUser = userRepository.findByfirebaseUId(userGuId);
		if(optionalUser.isPresent()) return optionalUser.get();
		String exceptionString = "No hi ha cap user amb ID: " + userGuId;
		throw new ApiRequestException(exceptionString);
	}
	
	private Comanda obtenirComanda(Long comandaId) {
		Optional<Comanda> optionalComanda = comandaRepository.findById(comandaId);
		if(optionalComanda.isPresent()) return optionalComanda.get();
		exceptionString = "No hi ha cap comanda amb ID: " + comandaId;
		throw new ApiRequestException(exceptionString);
	}
	
	private Botiga obtenirBotiga(Long botigaId) {
		Optional<Botiga> optionalBotiga = botigaRepository.findById(botigaId);
		if(optionalBotiga.isPresent()) return optionalBotiga.get();
		String exceptionString = "No hi ha cap botiga amb ID: " + botigaId;
		throw new ApiRequestException(exceptionString);
		
	}

	public List<ComandaDto> getComandesEntregades(Long userId) {
		User u = obtenirUser(userId);
		List<Comanda> comandes = u.getComandesUsuari();
		List<ComandaDto> comandesDto = new ArrayList<ComandaDto>();
		for(Comanda c : comandes) {
			if(c.getEntregada())comandesDto.add(new ComandaDto(c));
		}
		return comandesDto;
	}

	public List<ComandaDto> getComandesByFirebaseIdEntregades(String firebaseId) {
		User user = obtenirGuidUser(firebaseId);
		List<Comanda> comandes = user.getComandesUsuari();
		List<ComandaDto> comandesDto = new ArrayList<ComandaDto>();
		for(Comanda c: comandes) {
			if(c.getEntregada())comandesDto.add(new ComandaDto(c));
		}
		if (comandesDto.isEmpty()){
			exceptionString = "no Hi ha coses ";
			throw new ApiRequestException(exceptionString);
		}
		else {
			System.out.println("retorno les comandesDto");	
		return comandesDto;}
	}

}
