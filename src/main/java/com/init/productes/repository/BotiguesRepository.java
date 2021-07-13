package com.init.productes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.init.productes.entity.Botiga;
import com.init.productes.entity.User;

@Repository
public interface BotiguesRepository extends JpaRepository<Botiga,Long >{// clase i identificador
	
	// permet aconseguir una botiga pel seu districte
	List<Botiga> findBydistricte(String districte);
	
}
