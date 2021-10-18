package com.init.productes.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.init.productes.Dto.BotigaDto;
import com.init.productes.Dto.ComandaDto;
import com.init.productes.Dto.ProducteDto;
import com.init.productes.Dto.UserDto;
import com.init.productes.entity.Botiga;
import com.init.productes.entity.Comanda;
import com.init.productes.entity.Producte;
import com.init.productes.entity.User;
import com.init.productes.exception.ApiRequestException;
import com.init.productes.repository.BotiguesRepository;
import com.init.productes.repository.ComandaRepository;
import com.init.productes.repository.ProductesRespository;

@Service
public class BotigaService {
	
	@Autowired
	private BotiguesRepository botiguesRepository;
	
	@Autowired
	private ProductesRespository productesRepository;
	
	@Autowired
	private ComandaRepository comandaRepository;
	
	private String ExceptionString;

	public List<BotigaDto> getbotigues() {
		List<Botiga> botigues = botiguesRepository.findAll();
		List<BotigaDto> botigaDto = new ArrayList<BotigaDto>();
		if(botigues.isEmpty()) throw new ApiRequestException("No hi ha cap botiga insertada a la BD");
		for(Botiga b : botigues) {
			botigaDto.add(new BotigaDto(b));
		}
		return botigaDto;
	}
	
	public List<BotigaDto> getBotigaByDistricte(String districte) {
		List<Botiga> botigues = botiguesRepository.findBydistricte(districte);
		List<BotigaDto> botigaDto = new ArrayList<BotigaDto>();
		if(botigues.isEmpty()) throw new ApiRequestException("No hi ha cap botiga insertada a la BD");
		for(Botiga b : botigues) {
			botigaDto.add(new BotigaDto(b));
		}
		return botigaDto;
	}

	public BotigaDto getBotigaById(Long botigaId) {
		
			BotigaDto botigaDto = new BotigaDto(obtenirBotiga(botigaId));
			return botigaDto;
	}

	public void creteBotiga(Botiga botiga) {
		botiguesRepository.save(botiga);		
	}

	public void deleteById(Long botigaId) {
		Optional<Botiga> optionalBotiga = botiguesRepository.findById(botigaId);
		if(optionalBotiga.isPresent())botiguesRepository.deleteById(botigaId);
		else {
			ExceptionString = "No hi ha cap botiga amb Id:" + botigaId;
			throw new ApiRequestException(ExceptionString);
		}
	}

	public void updateBotiga(Botiga botiga) {
			Botiga updateBotiga = obtenirBotiga(botiga.getId());
			updateBotiga.setNom(botiga.getNom());
			updateBotiga.setDescripcio(botiga.getDescripcio());
			updateBotiga.setEmail(botiga.getEmail());
			updateBotiga.setLatitud(botiga.getLatitud());
			updateBotiga.setLongitud(botiga.getLongitud());
			updateBotiga.setTelefon(botiga.getTelefon());
			updateBotiga.setIconUrl(botiga.getIconUrl());
			botiguesRepository.save(updateBotiga);
	}

	public void afegirProducte(Long botigaId, Long productId) {
		
			Botiga botiga = obtenirBotiga(botigaId); //optenim la botiga
			Producte producte = obtenirProducte(productId);
			botiga.addProducteBotiga(producte);
			botiguesRepository.save(botiga);
			
	}
	
	public String deleteProducte(Long botigaId, Long productId) {
		Botiga botiga = obtenirBotiga(botigaId); //optenim la botiga
		Producte producte = obtenirProducte(productId);
		String retrn = botiga.deleteProducteBotiga(producte);
		botiguesRepository.save(botiga);
		return retrn;
		
	}
	public List<ProducteDto> getProductesBotiga(Long botigaId) {
		
		Botiga botiga = obtenirBotiga(botigaId); //optenim la botiga
		List<Producte> productes = botiga.getProductesBotiga();
		List<ProducteDto> productesDto = new ArrayList<ProducteDto>();
		for(Producte p : productes) {
			productesDto.add(new ProducteDto(p));
		}
		return productesDto;
	}

	public UserDto getPropietari(Long botigaId) {
			Botiga botiga = obtenirBotiga(botigaId);
			User propietari = botiga.getBotiguer();
			UserDto userDto = new UserDto(propietari);

			return userDto;
	}

	public void afegirComanda(Long botigaId, Long comandaId) {
		
			Botiga botiga =  obtenirBotiga(botigaId);
			Comanda comanda = obtenirComanda(comandaId);
			botiga.addComanda(comanda);
			comanda.setBotigaCompra(botiga);
			botiguesRepository.save(botiga);			
		
	}

	public List<ComandaDto> getComandes(Long botigaId) {
		Botiga botiga = obtenirBotiga(botigaId);
		List<Comanda> comandes = botiga.getComandesBotiga();
		List<ComandaDto> comandesDto = new ArrayList<ComandaDto>();
		for(Comanda c : comandes) {
			if(!c.getEntregada())comandesDto.add(new ComandaDto(c));
		}
		return comandesDto;
			
	}
	
	private Botiga obtenirBotiga(Long botigaId) {
		Optional<Botiga> optionalBotiga = botiguesRepository.findById(botigaId);
		if(optionalBotiga.isPresent()) return optionalBotiga.get();
		String exceptionString = "No hi ha cap botiga amb ID: " + botigaId;
		throw new ApiRequestException(exceptionString);
		
	}
	
	private Comanda obtenirComanda(Long comandaId) {
		Optional<Comanda> optionalComanda = comandaRepository.findById(comandaId);
		if(optionalComanda.isPresent()) return optionalComanda.get();
		String exceptionString = "No hi ha cap comanda amb ID: " + comandaId;
		throw new ApiRequestException(exceptionString);
		
	}
	
	private Producte obtenirProducte(Long producteId) {
		Optional<Producte> optionalProducte = productesRepository.findById(producteId);
		if(optionalProducte.isPresent()) return optionalProducte.get();
		String exceptionString = "No hi ha cap producte amb ID: " + producteId;
		throw new ApiRequestException(exceptionString);
	}


	public List<ComandaDto> getComandesAPreparar(Long botigaId) {
		// obtenim la botifa
		Botiga botiga = obtenirBotiga(botigaId);
		if(botiga.getComandesBotiga().isEmpty()) throw new ApiRequestException("la botiga no té comandes");
		List<ComandaDto> comandesAPreparar = new ArrayList<ComandaDto>();
		for(Comanda c : botiga.getComandesBotiga()) {
			if (!c.getPreparada()) comandesAPreparar.add(new ComandaDto(c));
		}
		if (comandesAPreparar.isEmpty()) throw new ApiRequestException("la botiga no té comandes");
		return comandesAPreparar;
	}

	public List<ComandaDto> getComandesEntregades(Long botigaId) {
		Botiga botiga = obtenirBotiga(botigaId);
		List<Comanda> comandes = botiga.getComandesBotiga();
		List<ComandaDto> comandesDto = new ArrayList<ComandaDto>();
		for(Comanda c : comandes) {
			if(c.getEntregada())comandesDto.add(new ComandaDto(c));
		}
		return comandesDto;
	}




		
	
	

}
