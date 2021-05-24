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
		
		Optional<Comanda> optionalComanda = comandaRepository.findById(comandaId);	
		if(optionalComanda.isPresent()) {
			ComandaDto comandaDto = new ComandaDto(optionalComanda.get());
			return comandaDto;
		}
		else {
			exceptionString = "No hi ha cap comanda amb ID:" + comandaId;
			throw new ApiRequestException(exceptionString);
		}

	}

	public void createComanda(Comanda comanda) {
		comanda.setEntregada(false);
		comanda.setPreparada(false);
		comanda.setCostTotal(0.0);
		comandaRepository.save(comanda);	
	}

	

	public UserDto getPropietari(Long comandaId) {
		Optional<Comanda> optionalComanda = comandaRepository.findById(comandaId);
		if(optionalComanda.isPresent()) {
			Comanda c = optionalComanda.get();
			UserDto userDto = new UserDto(c.getUserOwner());
			return userDto;			
		}
		else {
			exceptionString = "No hi ha cap comanda amb ID: " + comandaId;
			throw new ApiRequestException(exceptionString);
		}	
	}

	public List<ProducteDto> getProductescomanda(Long comandaId) {
		Optional<Comanda> optionalComanda = comandaRepository.findById(comandaId);
		if(optionalComanda.isPresent()) {
			Comanda c = optionalComanda.get();
			List<ProducteDto> productesDto = new ArrayList<ProducteDto>();
			for(ProducteQuantitat pq: c.getProductesComanda()) {
				productesDto.add(new ProducteDto(pq.getProducte()));
			}
			return productesDto;	
		}
		else {
			exceptionString = "No hi ha cap comanda amb ID: " + comandaId;
			throw new ApiRequestException(exceptionString);
		}
	}

	public BotigaDto getBotiga(Long comandaId) {
		Optional<Comanda> optionalComanda = comandaRepository.findById(comandaId);
		if(optionalComanda.isPresent()) {
			Comanda c = optionalComanda.get();
			Botiga b = c.getBotigaCompra();
			return new BotigaDto(b);
			
		}
		else {
			exceptionString = "No hi ha cap comanda amb ID: " + comandaId;
			throw new ApiRequestException(exceptionString);
		}
	}

	public void prepararComanda(Long comandaId) {
		Optional<Comanda> optionalComanda = comandaRepository.findById(comandaId);
		if(optionalComanda.isPresent()) {
			Comanda c = optionalComanda.get();
			c.setPreparada(true);
			comandaRepository.save(c);			
		}
		else {
			exceptionString = "No hi ha cap comanda amb ID: " + comandaId;
			throw new ApiRequestException(exceptionString);
		}
		
	}

	public void entregarComanda(Long comandaId) {
		Optional<Comanda> optionalComanda = comandaRepository.findById(comandaId);
		if(optionalComanda.isPresent()) {
			Comanda c = optionalComanda.get();
			c.setEntregada(true);
			comandaRepository.save(c);			
		}
		else {
			exceptionString = "No hi ha cap comanda amb ID: " + comandaId;
			throw new ApiRequestException(exceptionString);
		}
		
	}
	
	public void afegirProducteQuantitat(Long comandaId, Long producteId) {
		Optional<Comanda> optionalComanda = comandaRepository.findById(comandaId);
		Optional<ProducteQuantitat> optionalProducteQuantitat = pqRepository.findById(producteId);
		if(optionalComanda.isPresent()) {
			if(optionalProducteQuantitat.isPresent()) {
				Comanda comanda = optionalComanda.get();
				ProducteQuantitat producteQuantitat = optionalProducteQuantitat.get();
				comanda.addProducte(producteQuantitat);
				comanda.addPreuTotal(producteQuantitat.getProducte().getPreu());
				comandaRepository.save(comanda);
			}
			else {
				exceptionString = "No hi ha cap producteQuantitat amb ID: " + producteId;
				throw new ApiRequestException(exceptionString);
			}		
		}
		else {
			exceptionString = "No hi ha cap comanda amb ID: " + comandaId;
			throw new ApiRequestException(exceptionString);
		}	
	
		
	}

}
