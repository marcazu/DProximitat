package com.init.productes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.init.productes.entity.Producte;
import com.init.productes.entity.ProducteQuantitat;
import com.init.productes.entity.User;
import com.init.productes.exception.ApiRequestException;
import com.init.productes.repository.ProducteQuantitatRepository;
import com.init.productes.repository.ProductesRespository;

@Service
public class ProducteQuantitatService {
	
	@Autowired
	private ProducteQuantitatRepository pqRepository;
	@Autowired
	private ProductesRespository productesrepository;

	public List<ProducteQuantitat> getAll() {
		List<ProducteQuantitat> pq = pqRepository.findAll();
		if(pq.isEmpty()) throw new ApiRequestException("No hi ha cap producte quantitat a la BD");
		return pq;
	}

	public void crearPq(ProducteQuantitat pq) {
		pqRepository.save(pq);
		
	}

	public void deletePQ(Long pqId) {
		Optional<ProducteQuantitat> optionalPQ = pqRepository.findById(pqId);	
		if(optionalPQ.isPresent())pqRepository.deleteById(pqId);	 
		else {
			String exceptionString = "No hi ha cap ProducteQuantitat amb ID:" + pqId;
			throw new ApiRequestException(exceptionString);
		}
		
	}

	public void updatePQ(ProducteQuantitat pq) {
		ProducteQuantitat updatePQ = obtenirProducteQuantitat(pq.getId());
		updatePQ.setQuantitat(pq.getQuantitat());
		updatePQ.setProducte(pq.getProducte());
		pqRepository.save(updatePQ);		
	}

	public void linkarProducte(Long pqId, Long producteId) {
		
		ProducteQuantitat pq = obtenirProducteQuantitat(pqId);
		pq.setProducte(obtenirProducte(producteId));
		pqRepository.save(pq);
	}
	
	private ProducteQuantitat obtenirProducteQuantitat(Long producteQuantitatId) {
		Optional<ProducteQuantitat> optionalProducteQuantitat = pqRepository.findById(producteQuantitatId);
		if(optionalProducteQuantitat.isPresent()) return optionalProducteQuantitat.get();
		String exceptionString = "No hi ha cap comandaQuantitat amb ID: " + producteQuantitatId;
		throw new ApiRequestException(exceptionString);
	}
	
	private Producte obtenirProducte(Long producteId) {
		Optional<Producte> optionalProducte = productesrepository.findById(producteId);
		if(optionalProducte.isPresent()) return optionalProducte.get();
		String exceptionString = "No hi ha cap producte amb ID: " + producteId;
		throw new ApiRequestException(exceptionString);
	}

}
