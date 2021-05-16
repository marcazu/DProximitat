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

	public BotigaDto getBotigaById(Long botigaId) {
		Optional<Botiga> optionalBotiga = botiguesRepository.findById(botigaId);
		if(optionalBotiga.isPresent()) {
			BotigaDto botigaDto = new BotigaDto(optionalBotiga.get());
			return botigaDto;
		}
		else {
			ExceptionString = "No hi ha cap botiga amb ID:" + botigaId;
			throw new ApiRequestException(ExceptionString);
		}
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
		Optional<Botiga> optionalBotiga = botiguesRepository.findById(botiga.getId());		
		if(optionalBotiga.isPresent()) {
			Botiga updateBotiga = optionalBotiga.get();
			updateBotiga.setNom(botiga.getNom());
			updateBotiga.setDescripcio(botiga.getDescripcio());
			updateBotiga.setEmail(botiga.getEmail());
			updateBotiga.setLatitud(botiga.getLatitud());
			updateBotiga.setLongitud(botiga.getLongitud());
			updateBotiga.setTelefon(botiga.getTelefon());
			botiguesRepository.save(updateBotiga);
		}
		else {
			ExceptionString = "No hi ha cap botiga amb Id:" + botiga.getId();
			throw new ApiRequestException(ExceptionString);
		}
	}

	public void afegirProducte(Long botigaId, Long productId) {
		Optional<Botiga> optionalBotiga = botiguesRepository.findById(botigaId);//optenir botiga
		Optional<Producte> optionalProducte = productesRepository.findById(productId); //obtenir botiga
		if (optionalBotiga.isPresent()) {
			Botiga botiga = optionalBotiga.get(); //optenim la botiga
			if(optionalProducte.isPresent()) {
				Producte producte = optionalProducte.get();
				botiga.addProducteBotiga(producte);
				botiguesRepository.save(botiga);
			}
			else {
				ExceptionString = "No hi ha cap producte amb Id:" + productId;
				throw new ApiRequestException(ExceptionString);
			}
		}
		else {
			ExceptionString = "No hi ha cap botiga amb Id:" + botigaId;
			throw new ApiRequestException(ExceptionString);
		}
	}
	public List<ProducteDto> getProductesBotiga(Long botigaId) {
		Optional<Botiga> optionalBotiga = botiguesRepository.findById(botigaId);//optenir botiga
		if (optionalBotiga.isPresent()) {
			Botiga botiga = optionalBotiga.get(); //optenim la botiga
			List<Producte> productes = botiga.getProductesBotiga();
			List<ProducteDto> productesDto = new ArrayList<ProducteDto>();
			for(Producte p : productes) {
				productesDto.add(new ProducteDto(p));
			}
			return productesDto;
		}
		else {
			ExceptionString = "No hi ha cap botiga amb Id:" + botigaId;
			throw new ApiRequestException(ExceptionString);
		}
	}

	public UserDto getPropietari(Long botigaId) {
		Optional<Botiga> optionalBotiga = botiguesRepository.findById(botigaId);//optenir botiga
		if(optionalBotiga.isPresent()) {
			Botiga botiga = optionalBotiga.get();
			UserDto userDto = new UserDto(botiga.getBotiguer());
			return userDto;
		}
		else {
			ExceptionString = "No hi ha cap botiga amb Id:" + botigaId;
			throw new ApiRequestException(ExceptionString);
		}
	}

	public void afegirComanda(Long botigaId, Long comandaId) {
		Optional<Botiga> optionalBotiga = botiguesRepository.findById(botigaId);//optenir botiga
		Optional<Comanda> optionalComanda = comandaRepository.findById(comandaId);//optenir botiga
		if (optionalBotiga.isPresent()) {
			if(optionalComanda.isPresent()) {
				Botiga botiga = optionalBotiga.get();
				Comanda comanda = optionalComanda.get();
				botiga.addComanda(comanda);
				comanda.setBotigaCompra(botiga);
				botiguesRepository.save(botiga);
			}
			else {
				ExceptionString = "No hi ha cap comanda amb Id:" + comandaId;
				throw new ApiRequestException(ExceptionString);
			}
		}
		else {
			ExceptionString = "No hi ha cap botiga amb Id:" + botigaId;
			throw new ApiRequestException(ExceptionString);
		}
		
	}

	public List<ComandaDto> getComandes(Long botigaId) {
		Optional<Botiga> optionalBotiga = botiguesRepository.findById(botigaId);//optenir botiga
		if (optionalBotiga.isPresent()) {
			Botiga botiga = optionalBotiga.get();
			List<Comanda> comandes = botiga.getComandesBotiga();
			List<ComandaDto> comandesDto = new ArrayList<ComandaDto>();
			for(Comanda c : comandes) {
				comandesDto.add(new ComandaDto(c));
			}
			return comandesDto;
			
		}
		else {
			ExceptionString = "No hi ha cap botiga amb Id:" + botigaId;
			throw new ApiRequestException(ExceptionString);
		}
	}
	
	

}
