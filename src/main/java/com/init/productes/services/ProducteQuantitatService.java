package com.init.productes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.init.productes.Dto.ProducteQuantiatDto;
import com.init.productes.entity.Comanda;
import com.init.productes.entity.Producte;
import com.init.productes.entity.ProducteQuantitat;
import com.init.productes.entity.ProducteQuantitatID;
import com.init.productes.entity.User;
import com.init.productes.exception.ApiRequestException;
import com.init.productes.repository.ComandaRepository;
import com.init.productes.repository.ProducteQuantitatRepository;
import com.init.productes.repository.ProductesRespository;

@Service
public class ProducteQuantitatService {
	
	// de moment deixo la clase pero aquesta clase es podria borrar, serveix per testejar coses de moment
	
	@Autowired
	private ProducteQuantitatRepository pqRepository;
	@Autowired
	private ProductesRespository productesrepository;
	@Autowired
	private ComandaRepository comandaRepository;

	
	public void CrearProducteQuantiat(ProducteQuantiatDto pqDto) {
		// crea un nou producte quantitat amb la id de comanda i producte, s'ha d'afegir també al llistat de la comanda
		
		ProducteQuantitat pq = new ProducteQuantitat(new ProducteQuantitatID(pqDto.getComandaId(),pqDto.getProducteId()),
				pqDto.getQuantitat());
		pqRepository.save(pq);
		//obtenim la comanda i afegim el producteQuantitat
		Comanda c = obtenirComanda(pqDto.getComandaId());
		c.addProducteQuantitat(pq);
		comandaRepository.save(c);
		
	}
	public List<ProducteQuantitat> getByComandaId(Long comandaId) {
		List<ProducteQuantitat> pq = pqRepository.findByproducteQuantitatIDcomandaId(comandaId);
		if(pq.isEmpty()) throw new ApiRequestException("No hi ha cap producte quantitat a la BD");
		return pq;
	}
	public ProducteQuantitat getProducteQuantitat(Long comandaId, Long producteId) {
		Optional<ProducteQuantitat> pq = pqRepository.findById(new ProducteQuantitatID(comandaId,producteId));
		if(pq.isPresent()) return pq.get();
		else throw new ApiRequestException("No s'ha trobat el producte quantiat demanat");
	}

	public void deletePQ(Long comandaId, Long producteId) {
		Optional<ProducteQuantitat> optionalPQ = pqRepository.findById(new ProducteQuantitatID(comandaId,producteId));	
		if(optionalPQ.isPresent())pqRepository.deleteById(new ProducteQuantitatID(comandaId,producteId));	 
		else {
			String exceptionString = "No hi ha cap producte quantitat amb aquesta id";
			throw new ApiRequestException(exceptionString);
		}
		
	}

	//modifica la quantitat de productes que tenim al producteQuantitat
	public void modificarQuantitat(Long comandaId, Long producteId, int quantitat) {
		Optional<ProducteQuantitat> pq = pqRepository.findById(new ProducteQuantitatID(comandaId,producteId));
		if(pq.isPresent()) {
			ProducteQuantitat producteQuantitat = pq.get();
			producteQuantitat.setQuantitat(quantitat);
			pqRepository.save(producteQuantitat);
		}
		else {
			String exceptionString = "No hi ha cap producte quantitat amb aquesta id";
			throw new ApiRequestException(exceptionString);
		}
	}
	
	private int obtenirQuantitat(Long comandaId, Long producteId) {
		Optional<ProducteQuantitat> pq = pqRepository.findById(new ProducteQuantitatID(comandaId,producteId));
		if(pq.isPresent()) {
			ProducteQuantitat producteQuantitat = pq.get();
			return producteQuantitat.getQuantitat();
		}
		else {
			String exceptionString = "No hi ha cap producte quantitat amb aquesta id";
			throw new ApiRequestException(exceptionString);
		}
		
	}
	
	// funcio per obtenir una comanda a traves de la comanda Id
	private Comanda obtenirComanda(Long comandaId) {
		Optional<Comanda> optionalComanda = comandaRepository.findById(comandaId);
		if(optionalComanda.isPresent()) return optionalComanda.get();
		String exceptionString = "No hi ha cap comanda amb ID: " + comandaId;
		throw new ApiRequestException(exceptionString);
	}


}
