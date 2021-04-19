package com.init.productes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.init.productes.entity.Comanda;

@Repository
public interface ComandaRepository extends JpaRepository<Comanda,Long >{

}
