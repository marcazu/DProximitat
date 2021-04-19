package com.init.productes.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.init.productes.entity.User;
import com.init.productes.repository.BotiguesRepository;
import com.init.productes.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BotiguesRepository botigaRepository;
	
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	public User getUser(Long userId) {
		Optional<User> optionalUser = userRepository.findById(userId);		
		if(optionalUser.isPresent()) return optionalUser.get();
		return null;
	}

	public void deleteUser(Long userId) {
		userRepository.deleteById(userId);	
	}

	public int updateUser(User user) {
		Optional<User> optionalUser = userRepository.findById(user.getId());		
		if(optionalUser.isPresent()) {
			User updateUser = optionalUser.get();
			updateUser.setNom(user.getNom());
			updateUser.setEmail(user.getEmail());
			updateUser.setTelefon(user.getTelefon());
			updateUser.setEsBotiguer(user.getEsBotiguer());
			updateUser.setBotiguesUsuari(user.getBotiguesUsuari());
			userRepository.save(updateUser);
			return 1;  
		}
		return 0;// no troba el objecte
	}

	public void crearUser(User user) {
		userRepository.save(user);
		
	}
	
	
	
	

}
