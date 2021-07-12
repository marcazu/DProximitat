package com.init.productes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.init.productes.entity.Comanda;
import com.init.productes.entity.ProducteQuantitat;
import com.init.productes.entity.ProducteQuantitatID;

// retorna un producte quantiat per la seva Id
// s'ha de posar com a crudRepository no se pq
@Repository
public interface ProducteQuantitatRepository extends JpaRepository<ProducteQuantitat, ProducteQuantitatID> {
	
	// donat una comanda ID i un producteID retorna el producteQuantitat corresponent
	//ProducteQuantitat findByIdcomandaIdAndIdproducteId(Long comandaId, Long producteId);
	
	//List<ProducteQuantitat> findByIdNameAndIdArtist(String comanda, String artist);
	
	// retorna el conjunt de producte quantitat d'una comanda
	// s'ha de ser molt meticulos amb el nom de la funcio ja que es lo que utlitza per mapejar
	
	List<ProducteQuantitat> findBycomandaId(Long comandaId);
}
