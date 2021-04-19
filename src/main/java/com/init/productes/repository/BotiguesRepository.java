package com.init.productes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.init.productes.entity.Botiga;

@Repository
public interface BotiguesRepository extends JpaRepository<Botiga,Long >{// clase i identificador
	
}
