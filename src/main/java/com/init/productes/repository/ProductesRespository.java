package com.init.productes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.init.productes.entity.Producte;

public interface ProductesRespository extends JpaRepository<Producte,Long >{

}
