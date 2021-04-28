package com.init.productes.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.init.productes.entity.Producte;
import com.init.productes.exception.ApiRequestException;
import com.init.productes.repository.ProductesRespository;

@Service
public class ProducteService {
	
	@Autowired
	private ProductesRespository productRepository;
	
	private String ExceptionString;
	
	public List<Producte> getProductes() {
		List<Producte> productes = productRepository.findAll();
		if(productes.isEmpty()) throw new ApiRequestException("No hi ha cap producte insertat a la BD");
		return productRepository.findAll();
	}
	
	public Producte getProducteById(Long productId) {

		Optional<Producte> optionalProduct = productRepository.findById(productId);	
		if(optionalProduct.isPresent()) return optionalProduct.get();
		else {
			ExceptionString = "No hi ha cap producte amb ID:" + productId;
			throw new ApiRequestException(ExceptionString);
		}
		
	}

	public void createProducte(Producte producte) {
		productRepository.save(producte);
	}

	public void deleteById(Long productId) {
		Optional<Producte> optionalProduct = productRepository.findById(productId);	
		if(optionalProduct.isPresent()) productRepository.deleteById(productId);
		else {
			ExceptionString = "No hi ha cap producte amb ID:" + productId;
			throw new ApiRequestException(ExceptionString);
		}
		
	}

	public void updateProducte(Producte producte) {
		Optional<Producte> optionalProduct = productRepository.findById(producte.getId());		
		if(optionalProduct.isPresent()) {
			Producte updateProducte = optionalProduct.get();
			updateProducte.setNom(producte.getNom());
			updateProducte.setDescripcio(producte.getDescripcio());
			updateProducte.setTipus(producte.getTipus());
			updateProducte.setPreu(producte.getPreu());
			productRepository.save(updateProducte);
		}
		else {
			ExceptionString = "No hi ha cap producte amb ID:" + producte.getId();
			throw new ApiRequestException(ExceptionString);
		}
	}

}
