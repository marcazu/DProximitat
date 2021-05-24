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
	private ProductesRespository producterepository;

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
		Optional<ProducteQuantitat> optionalPQ = pqRepository.findById(pq.getId());		
		if(optionalPQ.isPresent()) {
			ProducteQuantitat updatePQ = optionalPQ.get();
			updatePQ.setQuantitat(pq.getQuantitat());
			updatePQ.setProducte(pq.getProducte());
			pqRepository.save(updatePQ);
		}
		else {
			String exceptionString = "No hi ha cap ProducteQuantitat amb ID:" + pq.getId();
			throw new ApiRequestException(exceptionString);
		}
		
	}

	public void linkarProducte(Long pqId, Long producteId) {
		Optional<ProducteQuantitat> optionalPQ = pqRepository.findById(pqId);
		Optional<Producte> optionalProducte = producterepository.findById(producteId);
		if(optionalPQ.isPresent()) {
			if(optionalProducte.isPresent()) {
				ProducteQuantitat pq = optionalPQ.get();
				pq.setProducte(optionalProducte.get());
				pqRepository.save(pq);
			}
			else {
				String exceptionString = "No hi ha cap Producte amb ID:" + producteId;
				throw new ApiRequestException(exceptionString);
			}
		}
		else {
			String exceptionString = "No hi ha cap ProducteQuantitat amb ID:" + pqId;
			throw new ApiRequestException(exceptionString);
		}


		
	}

}
