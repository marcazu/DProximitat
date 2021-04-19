package com.init.productes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.init.productes.entity.Comanda;
import com.init.productes.entity.Producte;
import com.init.productes.repository.ComandaRepository;

public class ComandaService {
	
	@Autowired
	private ComandaRepository comandaRepository;

	public List<Comanda> getComandes() {
		return comandaRepository.findAll();
	}

	public Comanda getProducteById(Long comandaId) {
		
		Optional<Comanda> optionalComanda = comandaRepository.findById(comandaId);	
		if(optionalComanda.isPresent()) return optionalComanda.get(); 
		return null;

	}

}
