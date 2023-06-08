package com.sportyshoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sportyshoes.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public List<User> findByUsernameAndPassword(String username,String password);
	public User findByUsername(String username);
}
