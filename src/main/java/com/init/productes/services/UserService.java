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
		Optional<User> optionalUser = userRepository.findById(userId);		
		if(optionalUser.isPresent()) {
			UserDto userDto = new UserDto(optionalUser.get());
			return userDto;
		}
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
			if(!user.getNom().isBlank()) updateUser.setNom(user.getNom());
			if(!user.getEmail().isBlank())updateUser.setEmail(user.getEmail());
			if(!user.getTelefon().isBlank())updateUser.setTelefon(user.getTelefon());
			//if(!user.getEsBotiguer().isBlank())updateUser.setEsBotiguer(user.getEsBotiguer());
			//if(!user.getEmail().isBlank())updateUser.setBotiguesUsuari(user.getBotiguesUsuari());
			userRepository.save(updateUser);
		}
		else {
			exceptionString = "No hi ha cap user amb ID:" + user.getId();
			throw new ApiRequestException(exceptionString);
		}
	}

	public void crearUser(User user) {
		user.setEsBotiguer(false);
		userRepository.save(user);
		
	}

	public void linkarBotiga(Long userId, Long botigaId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		Optional<Botiga> optionalBotiga = botigaRepository.findById(botigaId);
		
		if(optionalUser.isPresent()) {
			if(optionalBotiga.isPresent()) {
				User user = optionalUser.get();
				Botiga botiga = optionalBotiga.get();
				user.setEsBotiguer(true);
				user.addBotigaUser(botiga);
				botiga.setBotiguer(user);
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
		Optional<User> optionalUser = userRepository.findById(userId);
		Optional<Comanda> optionalComanda = comandaRepository.findById(comandaId);
		if(optionalUser.isPresent()) {
			if(optionalComanda.isPresent()) {
				User user = optionalUser.get();
				Comanda comanda = optionalComanda.get();
				comanda.setUserOwner(user);
				user.addComanda(comanda);//potserfalla aqui
				userRepository.save(user);
				return;
				
			}
			else {
				exceptionString = "No hi ha cap comanda amb ID: " + comandaId;
				throw new ApiRequestException(exceptionString);
			}
		}
		else {
			exceptionString = "No hi ha cap user amb ID: " + userId;
			throw new ApiRequestException(exceptionString);
		}
	}

	public List<ProducteDto> getCarro(Long userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		if(optionalUser.isPresent()) {
			User user = optionalUser.get();
			List<Producte> carro = user.getCarro();
			List<ProducteDto> carroDto = new ArrayList<ProducteDto>();
			for(Producte p :carro) {
				carroDto.add(new ProducteDto(p));
			}
			return carroDto;
			
		}
		else {
			exceptionString = "No hi ha cap user amb Id: " + userId;
			throw new ApiRequestException(exceptionString);
		}
	}

	public List<BotigaDto> getBotigues(Long userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		if(optionalUser.isPresent()) {
			User user = optionalUser.get();
			List<Botiga> botigues = user.getBotiguesUsuari();
			List<BotigaDto> botiguesDto = new ArrayList<BotigaDto>();
			for(Botiga b: botigues) {
				botiguesDto.add(new BotigaDto(b));
			}
			return botiguesDto;
		}
		else {
			exceptionString = "No hi ha cap user amb Id: " + userId;
			throw new ApiRequestException(exceptionString);
		}
	}

	public List<ComandaDto> getComandes(Long userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		if(optionalUser.isPresent()) {
			User user = optionalUser.get();
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
		else {
			exceptionString = "No hi ha cap user amb Id: " + userId;
			throw new ApiRequestException(exceptionString);
		}
	}

	public UserDto getUserFireBase(String userId) {
		// TODO Auto-generated method stub
		Optional<User> optionalUser = userRepository.findByfirebaseUId(userId);
		if(optionalUser.isPresent()) {
			UserDto userDto = new UserDto(optionalUser.get());
			return userDto;
			
		}
		
		else {
			exceptionString = "No hi ha cap user amb Id: " + userId;
			throw new ApiRequestException(exceptionString);
		}
	}

}
