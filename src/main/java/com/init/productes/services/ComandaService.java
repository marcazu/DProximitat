package com.init.productes.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.init.productes.Dto.BotigaDto;
import com.init.productes.Dto.ComandaDto;
import com.init.productes.Dto.UserDto;
import com.init.productes.entity.Botiga;
import com.init.productes.entity.Comanda;
import com.init.productes.entity.Producte;
import com.init.productes.entity.User;
import com.init.productes.exception.ApiRequestException;
import com.init.productes.repository.ComandaRepository;
import com.init.productes.repository.ProductesRespository;

@Service
public class ComandaService {
	
	@Autowired
	private ComandaRepository comandaRepository;
	@Autowired
	private ProductesRespository producteRepository;
	
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
		
		Optional<Comanda> optionalComanda = comandaRepository.findById(comandaId);	
		if(optionalComanda.isPresent()) {
			ComandaDto comandaDto = new ComandaDto(optionalComanda.get());
			return comandaDto;
		}
		else {
			exceptionString = "No hi ha cap comanda amb ID:" + comandaId;
			throw new ApiRequestException(exceptionString);
		}

	}

	public void createComanda(Comanda comanda) {
		comandaRepository.save(comanda);	
	}

	public void afegirProducte(Long comandaId, Long producteId) {
		Optional<Comanda> optionalComanda = comandaRepository.findById(comandaId);
		Optional<Producte> optionalProducte = producteRepository.findById(producteId);
		if(optionalComanda.isPresent()) {
			if(optionalProducte.isPresent()) {
				Comanda comanda = optionalComanda.get();
				Producte producte = optionalProducte.get();
				comanda.addProducte(producte);
				comandaRepository.save(comanda);
			}
			else {
				exceptionString = "No hi ha cap producte amb ID: " + producteId;
				throw new ApiRequestException(exceptionString);
			}		
		}
		else {
			exceptionString = "No hi ha cap comanda amb ID: " + comandaId;
			throw new ApiRequestException(exceptionString);
		}	
	
		
	}

	public UserDto getPropietari(Long comandaId) {
		Optional<Comanda> optionalComanda = comandaRepository.findById(comandaId);
		if(optionalComanda.isPresent()) {
			Comanda c = optionalComanda.get();
			UserDto userDto = new UserDto(c.getUserOwner());
			return userDto;			
		}
		else {
			exceptionString = "No hi ha cap comanda amb ID: " + comandaId;
			throw new ApiRequestException(exceptionString);
		}	
	}

	public List<Producte> getProductescomanda(Long comandaId) {
		Optional<Comanda> optionalComanda = comandaRepository.findById(comandaId);
		if(optionalComanda.isPresent()) {
			Comanda c = optionalComanda.get();
			return c.getProductesComanda();	
		}
		else {
			exceptionString = "No hi ha cap comanda amb ID: " + comandaId;
			throw new ApiRequestException(exceptionString);
		}
	}

	public BotigaDto getBotiga(Long comandaId) {
		Optional<Comanda> optionalComanda = comandaRepository.findById(comandaId);
		if(optionalComanda.isPresent()) {
			Comanda c = optionalComanda.get();
			Botiga b = c.getBotigaCompra();
			return new BotigaDto(b);
			
		}
		else {
			exceptionString = "No hi ha cap comanda amb ID: " + comandaId;
			throw new ApiRequestException(exceptionString);
		}
	}

}
