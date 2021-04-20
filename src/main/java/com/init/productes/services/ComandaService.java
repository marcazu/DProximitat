package com.init.productes.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.init.productes.entity.Comanda;
import com.init.productes.exception.ApiRequestException;
import com.init.productes.repository.ComandaRepository;

@Service
public class ComandaService {
	
	@Autowired
	private ComandaRepository comandaRepository;
	
	private String ExceptionString;

	public List<Comanda> getComandes() {
		return comandaRepository.findAll();
	}

	public Comanda getProducteById(Long comandaId) {
		
		Optional<Comanda> optionalComanda = comandaRepository.findById(comandaId);	
		if(optionalComanda.isPresent()) return optionalComanda.get();
		else {
			ExceptionString = "No hi ha cap comanda amb ID:" + comandaId;
			throw new ApiRequestException(ExceptionString);
		}

	}

	public void createComanda(Comanda comanda) {
		comandaRepository.save(comanda);	
	}

}
