package com.init.productes.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.init.productes.Dto.BotigaDto;
import com.init.productes.Dto.ComandaDto;
import com.init.productes.Dto.ComandaLinkarDto;
import com.init.productes.Dto.ProducteDto;
import com.init.productes.Dto.UserDto;
import com.init.productes.entity.Botiga;
import com.init.productes.entity.Comanda;
import com.init.productes.entity.Producte;
import com.init.productes.entity.ProducteQuantitat;
import com.init.productes.entity.User;
import com.init.productes.exception.ApiRequestException;
import com.init.productes.repository.BotiguesRepository;
import com.init.productes.repository.ComandaRepository;
import com.init.productes.repository.ProducteQuantitatRepository;
import com.init.productes.repository.ProductesRespository;
import com.init.productes.repository.UserRepository;

@Service
public class ComandaService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ComandaRepository comandaRepository;
	@Autowired
	private ProductesRespository producteRepository;
	@Autowired
	private BotiguesRepository botigaRepository;
	@Autowired
	private ProducteQuantitatRepository pqRepository;
	
	private String exceptionString;

	public List<ComandaDto> getComandes() {
		List<Comanda> comandes = comandaRepository.findAll();
		List<ComandaDto> comandesDto = new ArrayList<ComandaDto>();
		if(comandes.isEmpty()) {
			exceptionString = "No hi ha cap comanda a la BD:";
			throw new ApiRequestException(exceptionString);
		}
		for(Comanda c : comandes) {
			comandesDto.add(new ComandaDto(c));
			
		}
		return comandesDto;
	}

	public ComandaDto getProducteById(Long comandaId) {
		
		ComandaDto comandaDto = new ComandaDto(obtenirComanda(comandaId));
		return comandaDto;
	}

	public String createComanda(Comanda comanda) {
		comanda.setEntregada(false);
		comanda.setPreparada(false);
		comanda.setCostTotal(0.0);
		comandaRepository.save(comanda);	
		return String.valueOf(comanda.getId());
	}

	

	public UserDto getPropietari(Long comandaId) {
		Comanda c = obtenirComanda(comandaId);
		UserDto userDto = new UserDto(c.getUserOwner());
		return userDto;		
	}
/*
	public List<ProducteDto> getProductescomanda(Long comandaId) {
		
		Comanda c = obtenirComanda(comandaId);
		List<ProducteDto> productesDto = new ArrayList<ProducteDto>();
		for(ProducteQuantitat pq: c.getProductesComanda()) {
			productesDto.add(new ProducteDto(pq.getProducte()));
		}
		return productesDto;	
	}
	*/

	public BotigaDto getBotiga(Long comandaId) {
		
		Comanda c = obtenirComanda(comandaId);
		Botiga b = c.getBotigaCompra();
		return new BotigaDto(b);
	}

	public void prepararComanda(Long comandaId) {
		
		Comanda c = obtenirComanda(comandaId);
		c.setPreparada(true);
		comandaRepository.save(c);			
	}

	public void entregarComanda(Long comandaId) {
		Comanda c = obtenirComanda(comandaId);
		c.setEntregada(true);
		comandaRepository.save(c);				
	}
	
	/*
	public void afegirProducteQuantitat(Long comandaId, Long producteId) {
		
		Comanda comanda = obtenirComanda(comandaId);
		ProducteQuantitat producteQuantitat = obtenirProducteQuantitat(producteId);
		comanda.addProducteQuantitat(producteQuantitat);
		comanda.addPreuTotal((producteQuantitat.getProducte()).getPreu());
		comandaRepository.save(comanda);
		
	}
	*/

	public void crearLinkarComanda(ComandaLinkarDto comandaLinkarDto) {
		Comanda c = new Comanda();
		Botiga b = obtenirBotiga(Long.valueOf(comandaLinkarDto.getBotigaID()));
		User u = obtenirUser(Long.valueOf(comandaLinkarDto.getUserID()));
		c.setBotigaCompra(b);
		c.setUserOwner(u);
		b.addComanda(c);
		u.addComanda(c);

		comandaRepository.save(c);
		userRepository.save(u);
		botigaRepository.save(b);
		
	}

	public Long createEmptyComanda() {
		Comanda c = new Comanda();
		comandaRepository.save(c);
		return c.getId();
	}
	
	private Comanda obtenirComanda(Long comandaId) {
		Optional<Comanda> optionalComanda = comandaRepository.findById(comandaId);
		if(optionalComanda.isPresent()) return optionalComanda.get();
		exceptionString = "No hi ha cap comanda amb ID: " + comandaId;
		throw new ApiRequestException(exceptionString);
	}
	
	private Botiga obtenirBotiga(Long string) {
		Optional<Botiga> optionalBotiga = botigaRepository.findById(string);
		if(optionalBotiga.isPresent()) return optionalBotiga.get();
		String exceptionString = "No hi ha cap botiga amb ID: " + string;
		throw new ApiRequestException(exceptionString);
	}
	
	private User obtenirUser(Long userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		if(optionalUser.isPresent()) return optionalUser.get();
		String exceptionString = "No hi ha cap user amb ID: " + userId;
		throw new ApiRequestException(exceptionString);
	}
	
	private void linkarComandaBotiga(Long comandaId, Long botigaId) {
		Botiga botiga =  obtenirBotiga(botigaId);
		Comanda comanda = obtenirComanda(botigaId);
		botiga.addComanda(comanda);
		comanda.setBotigaCompra(botiga);
		botigaRepository.save(botiga);
		
	}
	
	private void linkarComandaUser(Long comandaID,Long userID) {
		User user = obtenirUser(userID);
		Comanda comanda = obtenirComanda(comandaID);
		comanda.setUserOwner(user);
		user.addComanda(comanda);//potserfalla aqui
		userRepository.save(user);
		
	}
	


}
