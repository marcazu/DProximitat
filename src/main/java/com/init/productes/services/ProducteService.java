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
	
	public List<Producte> getProductes() {
		System.out.println("arribo al service");
		return productRepository.findAll();
	}
	
	public Producte getProducteById(Long productId) {

		Optional<Producte> optionalProduct = productRepository.findById(productId);	
		if(optionalProduct.isPresent()) return optionalProduct.get(); 
		throw new ApiRequestException("No hi ha cap producte amb aquesta id");
		
	}

	public void createProducte(Producte producte) {
		productRepository.save(producte);
	}

	public void deleteById(Long productId) {
		productRepository.deleteById(productId);
	}

	public int updateProducte(Producte producte) {
		Optional<Producte> optionalProduct = productRepository.findById(producte.getId());		
		if(optionalProduct.isPresent()) {
			Producte updateProducte = optionalProduct.get();
			updateProducte.setNom(producte.getNom());
			updateProducte.setDescripcio(producte.getDescripcio());
			updateProducte.setTipus(producte.getTipus());
			productRepository.save(updateProducte);
			return 1; 
		}
		return 0;
	}

}
