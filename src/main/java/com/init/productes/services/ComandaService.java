package com.init.productes.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

	public List<Comanda> getComandes() {
		return comandaRepository.findAll();
	}

	public Comanda getProducteById(Long comandaId) {
		
		Optional<Comanda> optionalComanda = comandaRepository.findById(comandaId);	
		if(optionalComanda.isPresent()) return optionalComanda.get();
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

}
