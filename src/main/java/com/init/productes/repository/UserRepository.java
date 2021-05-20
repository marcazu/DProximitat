package com.init.productes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.init.productes.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long >{
	
	Optional<User> findByfirebaseUId(String firebaseUId);

}
