package com.init.productes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.init.productes.entity.Botiga;
import com.init.productes.entity.Producte;
import com.init.productes.repository.BotiguesRepository;
import com.init.productes.repository.ProductesRespository;

public class BotigaService {
	
	@Autowired
	private BotiguesRepository botiguesRepository;
	
	@Autowired
	private ProductesRespository productesRepository;

	public List<Botiga> getbotigues() {
		return botiguesRepository.findAll();
	}

	public Botiga getBotigaById(Long botigaId) {
		Optional<Botiga> optionalBotiga = botiguesRepository.findById(botigaId);
		if(optionalBotiga.isPresent())return optionalBotiga.get();
		else return null;
	}

	public void creteBotiga(Botiga botiga) {
		botiguesRepository.save(botiga);		
	}

	public void deleteById(Long botigaId) {
		botiguesRepository.deleteById(botigaId);
	}

	public int updateBotiga(Botiga botiga) {
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
			return 1;
		}
		return 0;
	}

	public int afegirProducte(Long botigaId, Long productId) {
		Optional<Botiga> optionalBotiga = botiguesRepository.findById(botigaId);//optenir botiga
		Optional<Producte> optionalProducte = productesRepository.findById(productId); //obtenir botiga
		if (optionalBotiga.isPresent()) {
			Botiga botiga = optionalBotiga.get(); //optenim la botiga
			if(optionalProducte.isPresent()) {
				Producte producte = optionalProducte.get();
				botiga.addProducteBotiga(producte);
				botiguesRepository.save(botiga);
				return 1;
			}
			return 0;
		}
		return 0;
	}
	public List<Producte> getProductesBotiga(Long botigaId) {
		Optional<Botiga> optionalBotiga = botiguesRepository.findById(botigaId);//optenir botiga
		if (optionalBotiga.isPresent()) {
			Botiga botiga = optionalBotiga.get(); //optenim la botiga
			return botiga.getProductesBotiga();
		}
		return null;
	}
	
	

}
