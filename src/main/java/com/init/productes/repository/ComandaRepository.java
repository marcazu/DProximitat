package com.init.productes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.init.productes.entity.Comanda;


public interface ComandaRepository extends JpaRepository<Comanda,Long >{

}
