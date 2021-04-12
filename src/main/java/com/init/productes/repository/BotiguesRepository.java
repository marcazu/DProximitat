package com.init.productes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.init.productes.entity.Botiga;

public interface BotiguesRepository extends JpaRepository<Botiga,Long >{// clase i identificador
	
}
