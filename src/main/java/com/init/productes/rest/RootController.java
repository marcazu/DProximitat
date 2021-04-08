package com.init.productes.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class RootController {
	@GetMapping
	public String hello() {
		return("hola, encara no funciona tot, estic fent proves");
		
	}

}
