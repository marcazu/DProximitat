package com.init.productes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.init.productes.entity.Producte;

@Repository
public interface ProductesRespository extends JpaRepository<Producte,Long >{

}
