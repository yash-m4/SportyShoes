package com.sportyshoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshoes.model.User;
import com.sportyshoes.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public void addUser(User user) {
		userRepository.save(user);
	}
	
	public int authUser(String username,String password) {
		
		if(userRepository.findByUsernameAndPassword(username, password).size()>0) {
			return 1;
		}
		
		else {
			return 2;
		}
		
	}
	
	public List<User> viewAllUsers(){
		return userRepository.findAll();
	}
	
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	public void updateUser(User user) {
		userRepository.save(user);
		}
}
