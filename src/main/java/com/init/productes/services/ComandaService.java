package com.init.productes.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.init.productes.Dto.BotigaDto;
import com.init.productes.Dto.ComandaDto;
import com.init.productes.Dto.ProducteDto;
import com.init.productes.Dto.UserDto;
import com.init.productes.entity.Botiga;
import com.init.productes.entity.Comanda;
import com.init.productes.entity.Producte;
import com.init.productes.entity.ProducteQuantitat;
import com.init.productes.entity.User;
import com.init.productes.exception.ApiRequestException;
import com.init.productes.repository.ComandaRepository;
import com.init.productes.repository.ProducteQuantitatRepository;
import com.init.productes.repository.ProductesRespository;

@Service
public class ComandaService {
	
	@Autowired
	private ComandaRepository comandaRepository;
	@Autowired
	private ProductesRespository producteRepository;
	@Autowired
	private ProducteQuantitatRepository pqRepository;
	
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
		
		ComandaDto comandaDto = new ComandaDto(obtenirComanda(comandaId));
		return comandaDto;
	}

	public String createComanda(Comanda comanda) {
		comanda.setEntregada(false);
		comanda.setPreparada(false);
		comanda.setCostTotal(0.0);
		comandaRepository.save(comanda);	
		return String.valueOf(comanda.getId());
	}

	

	public UserDto getPropietari(Long comandaId) {
		Comanda c = obtenirComanda(comandaId);
		UserDto userDto = new UserDto(c.getUserOwner());
		return userDto;		
	}

	public List<ProducteDto> getProductescomanda(Long comandaId) {
		
		Comanda c = obtenirComanda(comandaId);
		List<ProducteDto> productesDto = new ArrayList<ProducteDto>();
		for(ProducteQuantitat pq: c.getProductesComanda()) {
			productesDto.add(new ProducteDto(pq.getProducte()));
		}
		return productesDto;	
	}

	public BotigaDto getBotiga(Long comandaId) {
		
		Comanda c = obtenirComanda(comandaId);
		Botiga b = c.getBotigaCompra();
		return new BotigaDto(b);
	}

	public void prepararComanda(Long comandaId) {
		
		Comanda c = obtenirComanda(comandaId);
		c.setPreparada(true);
		comandaRepository.save(c);			
	}

	public void entregarComanda(Long comandaId) {
		Comanda c = obtenirComanda(comandaId);
		c.setEntregada(true);
		comandaRepository.save(c);				
	}
	
	public void afegirProducteQuantitat(Long comandaId, Long producteId) {
		
		Comanda comanda = obtenirComanda(comandaId);
		ProducteQuantitat producteQuantitat = obtenirProducteQuantitat(producteId);
		comanda.addProducteQuantitat(producteQuantitat);
		comanda.addPreuTotal((producteQuantitat.getProducte()).getPreu());
		comandaRepository.save(comanda);
		
	}
	
	private Comanda obtenirComanda(Long comandaId) {
		Optional<Comanda> optionalComanda = comandaRepository.findById(comandaId);
		if(optionalComanda.isPresent()) return optionalComanda.get();
		exceptionString = "No hi ha cap comanda amb ID: " + comandaId;
		throw new ApiRequestException(exceptionString);
	}
	private ProducteQuantitat obtenirProducteQuantitat(Long producteQuantitatId) {
		Optional<ProducteQuantitat> optionalProducteQuantitat = pqRepository.findById(producteQuantitatId);
		if(optionalProducteQuantitat.isPresent()) return optionalProducteQuantitat.get();
		exceptionString = "No hi ha cap comandaQuantitat amb ID: " + producteQuantitatId;
		throw new ApiRequestException(exceptionString);
	}


}
