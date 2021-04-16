package com.init.productes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.init.productes.entity.User;

public interface UserRepository extends JpaRepository<User,Long >{

}
