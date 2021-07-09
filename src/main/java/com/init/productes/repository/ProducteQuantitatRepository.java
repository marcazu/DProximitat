package com.init.productes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.init.productes.entity.Comanda;
import com.init.productes.entity.ProducteQuantitat;
import com.init.productes.entity.ProducteQuantitatID;

// retorna un producte quantiat per la seva Id
@Repository
public interface ProducteQuantitatRepository extends CrudRepository<ProducteQuantitat, ProducteQuantitatID> {
	
	// donat una comanda id retorna tots els seus productes quantitat
	List<ProducteQuantitat> findProducteQuantiatByComandaId(Long comandaId);

}
