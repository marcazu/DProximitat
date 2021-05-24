package com.init.productes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.init.productes.entity.Comanda;
import com.init.productes.entity.ProducteQuantitat;

@Repository
public interface ProducteQuantitatRepository extends JpaRepository<ProducteQuantitat,Long>{

}
